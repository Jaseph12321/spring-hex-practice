package com.example.springhexpractice.domain.foo.aggregate.service;

import com.example.springhexpractice.domain.command.TicketCommand;
import com.example.springhexpractice.iface.rest.dto.request.CreateTicket;
import com.example.springhexpractice.domain.foo.aggregate.entity.Train;
import com.example.springhexpractice.domain.foo.aggregate.entity.TrainStop;
import com.example.springhexpractice.config.exception.CheckErrorException;
import com.example.springhexpractice.infra.repository.TrainRepository;
import com.example.springhexpractice.infra.utils.ErrorInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component("ticketDomainService")
@Slf4j
public class TicketDomainService implements DomainService<TicketCommand> {

    @Autowired
    private TrainRepository trainRepository;

    @Override
    public void validation(TicketCommand ticketCommand) throws CheckErrorException {
        List<ErrorInfo> errorInfo = new ArrayList<>();
        List<String> errorList = new ArrayList<>();

        checkDateFormat(ticketCommand.getTakeDate(), errorInfo, errorList);
        checkTrainNotExists(ticketCommand.getTrainNo(), errorInfo, errorList);
        checkTicketMakeSense(ticketCommand, errorInfo, errorList);

        if (errorInfo.size() > 0) {
            for (String e : errorList)
                log.error(e);
            throw new CheckErrorException(errorInfo);
        }

    }


    //below are methods
    //查詢日期格式是否正確
    private void checkDateFormat(String dateTime, List<ErrorInfo> errorInfo, List<String> errorList) {
        try {
            SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");
            Date date = d.parse(dateTime);
        } catch (ParseException e) {
            errorList.add("create ticket date format is not right");
            errorInfo.add(ErrorInfo.DatePatternInvalid);
        }
    }

    //檢查車次是否已存在
    private void checkTrainNotExists(int trainNo, List<ErrorInfo> errorInfo, List<String> errorList) {
        if (null == trainRepository.findByTrainNo(trainNo)) {
            errorList.add("the train is not exist");
            errorInfo.add(ErrorInfo.TrainNoExists);
        }
    }

    //各個站點的順序是否正確
    private void checkTicketMakeSense(TicketCommand ticketCommand, List<ErrorInfo> errorInfo, List<String> errorList) {
        System.out.println(ticketCommand.getTrainNo());
        Train train = trainRepository.findByTrainNo(ticketCommand.getTrainNo());
        System.out.println(train.getUuid());
        int start=0;
        for(TrainStop trainStop: train.getTrainStops()){
            if(trainStop.getName().equals(ticketCommand.getFromStop())){
                start = trainStop.getSeq();
                break;
            }
        }

        for(int i = 0; i <=start;i++){
            if(train.getTrainStops().get(i).getName().equals(ticketCommand.getToStop())){
                    errorList.add("create ticket stop is invalid");
                    errorInfo.add(ErrorInfo.TicketStopsInvalid);
                    break;
            }
        }
//        TrainStop startStop = trainStopRepository.findByTrainUuidAndName(train.getUuid(), createTicket.getFromStop());
//        TrainStop finishStop = trainStopRepository.findByTrainUuidAndName(train.getUuid(), createTicket.getToStop());
//        if (startStop.getSeq() >= finishStop.getSeq()) {
//            errorList.add("create ticket stop is invalid");
//            errorInfo.add(ErrorInfo.TicketStopsInvalid);
//        }
    }
}
