package com.example.springhexpractice.domain.foo.aggregate.service;

import com.example.springhexpractice.domain.command.StopsCommand;
import com.example.springhexpractice.domain.command.TicketCommand;
import com.example.springhexpractice.domain.command.TrainCommand;
import com.example.springhexpractice.iface.rest.dto.request.CreateStops;
import com.example.springhexpractice.iface.rest.dto.request.CreateTrain;
import com.example.springhexpractice.domain.foo.aggregate.entity.Train;
import com.example.springhexpractice.config.exception.CheckErrorException;
import com.example.springhexpractice.config.exception.DataNotFoundException;
import com.example.springhexpractice.infra.repository.TrainRepository;
import com.example.springhexpractice.infra.outbound.OutboundService;
import com.example.springhexpractice.infra.utils.ErrorInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Component("trainDomainService")
@Slf4j
public class TrainDomainService implements DomainService<TrainCommand> {
    private static final String[] places = {"屏東", "高雄", "臺南", "嘉義", "彰化", "台中", "苗粟", "新竹", "桃園", "樹林",
            "板橋", "萬華", "台北", "松山", "南港", "汐止", "基隆"};

    @Autowired
    OutboundService outboundService;
    @Autowired
    private TrainRepository trainRepository;

    //check starter
    @Override
    public void validation(TrainCommand trainCommand) throws CheckErrorException, DataNotFoundException {
        outboundService.checkTrainStatus(trainCommand);

        List<ErrorInfo> errorInfo = new ArrayList<>();
        List<String> errorList = new ArrayList<>();

        checkNoExists(trainRepository.findByTrainNo(trainCommand.getTrainNo()), errorInfo, errorList);
        checkDuplicate(trainCommand, errorInfo, errorList);
        if (stopTimeNotRight(trainCommand.getStops()) || stopPositionNotRight(trainCommand.getStops()))
            errorInfo.add(ErrorInfo.TrainStopsNotSorted);

        if (errorInfo.size() > 0) {
            for (String e : errorList)
                log.error(e);
            throw new CheckErrorException(errorInfo);
        }
    }


    //below are methods

    public void checkNoExists(Train train, List<ErrorInfo> errorInfo, List<String> errorList) {
        if (null != train) {
            errorList.add("Train number is already exists");
            errorInfo.add(ErrorInfo.TrainNoExists);
        }
    }

    // 查詢車次是否有重複的站名
    public void checkDuplicate(TrainCommand trainCommand, List<ErrorInfo> errorInfo, List<String> errorList) {
        Set<String> stopNameSet = new HashSet<>();
        List<StopsCommand> stopList = trainCommand.getStops();
        for (StopsCommand n : stopList) {
            if (stopNameSet.contains(n.getStop_name())) {
                errorList.add("Train stop is duplicate");
                errorInfo.add(ErrorInfo.TrainStopsDuplicate);
                break;
            }
            stopNameSet.add(n.getStop_name());
        }
    }

    //檢查站點順序
    public boolean stopPositionNotRight(List<StopsCommand> stopsList) {
        List<String> stops = new ArrayList<>();
        for (StopsCommand n : stopsList) {
            System.out.println(n.getStop_name());
            stops.add(n.getStop_name());
        }


        List<Integer> stopnumbers = new ArrayList<>();
        for (String s : stops) {
            for (int j = 0; j < places.length; j++) {
                if (s.equals(places[j])) {
                    System.out.println(j + "place");
                    stopnumbers.add(j);
                    break;
                }
            }

        }
        if (stopnumbers.get(0) < stopnumbers.get(stopnumbers.size() - 1)) {
            for (int i = 0; i < stopnumbers.size(); i++) {
                if (stopnumbers.get(i) > stopnumbers.get(i + 1))
                    return true;
            }
        } else if (stopnumbers.get(0) > stopnumbers.get(stopnumbers.size() - 1)) {
            log.info("start decline");
            for (int i = 0; i < stopnumbers.size() - 1; i++) {
                if (stopnumbers.get(i) < stopnumbers.get(i + 1))
                    return true;
            }
        }
        return false;
    }

    //    檢查時間順序是否正確
    public boolean stopTimeNotRight(List<StopsCommand> stopsList) {
        for (int i = 0; i < stopsList.size() - 1; i++) {
            if (stopsList.get(i).getStop_time().isAfter(stopsList.get(i + 1).getStop_time()))
                return true;
        }
        return false;
    }


}
