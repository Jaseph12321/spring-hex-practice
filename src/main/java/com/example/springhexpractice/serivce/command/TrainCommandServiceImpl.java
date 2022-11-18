package com.example.springhexpractice.serivce.command;

import com.example.springhexpractice.domain.command.StopsCommand;
import com.example.springhexpractice.domain.command.TrainCommand;
import com.example.springhexpractice.domain.foo.aggregate.service.DomainService;
import com.example.springhexpractice.iface.rest.dto.request.CreateStops;
import com.example.springhexpractice.iface.rest.dto.request.CreateTrain;
import com.example.springhexpractice.domain.foo.aggregate.entity.Train;
import com.example.springhexpractice.domain.foo.aggregate.entity.TrainStop;
import com.example.springhexpractice.config.exception.CheckErrorException;
import com.example.springhexpractice.config.exception.DataNotFoundException;
import com.example.springhexpractice.infra.repository.TrainRepository;
import com.example.springhexpractice.infra.outbound.OutboundService;
import com.example.springhexpractice.serivce.ApplicationService;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service("TrainCommandService")
@Slf4j
public class TrainCommandServiceImpl implements TrainCommandService {


    @Autowired
    ApplicationService applicationService;


    @Autowired
    TrainRepository trainRepository;

    @Autowired
    private OutboundService outboundService;

    @Autowired
    @Qualifier("trainDomainService")
    DomainService<TrainCommand> trainDomainService;

    @Override
    @Transactional
    public Map<String, String> createNewTrain(TrainCommand trainCommand) throws DataNotFoundException, CheckErrorException {

        List<TrainStop> trainStopList = new ArrayList<>();
        String trainUuid = RandomString.make(32);
        log.info("go into validation phase");
        trainDomainService.validation(trainCommand);
        log.info("validation successful");
        log.info("start create train stop info");
        int seq = 1;
        for (StopsCommand n : trainCommand.getStops()) {
            TrainStop trainStop = TrainStop.create(n, seq, trainUuid);
            trainStopList.add(trainStop);
            seq++;
        }
        log.info("new train stop successfully created");
        Train train = Train.create(trainCommand, trainUuid,trainStopList);
        this.trainRepository.save(train);
        log.info("train stop informations saved");
        return applicationService.returnResult(train.getUuid());
    }


}
