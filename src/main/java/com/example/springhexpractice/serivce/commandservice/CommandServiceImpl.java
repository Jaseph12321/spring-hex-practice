package com.example.springhexpractice.serivce.commandservice;

import com.example.springhexpractice.controller.dto.request.CreateStops;
import com.example.springhexpractice.controller.dto.request.CreateTicket;
import com.example.springhexpractice.controller.dto.request.CreateTrain;
import com.example.springhexpractice.entity.Train;
import com.example.springhexpractice.entity.TrainStop;
import com.example.springhexpractice.entity.TrainTicket;
import com.example.springhexpractice.repository.TrainRepository;
import com.example.springhexpractice.repository.TrainStopRepository;
import com.example.springhexpractice.repository.TrainTicketRepository;
import com.example.springhexpractice.serivce.domainservice.DomainService;
import com.example.springhexpractice.serivce.outboundservice.OutboundService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Map;

@Service("CommandService")
@Slf4j
public class CommandServiceImpl implements CommandService {


    @Autowired
    TrainRepository trainRepository;

    @Autowired
    DomainService domainService;

    @Autowired
    OutboundService outboundService;

    @Autowired
    TrainStopRepository trainStopRepository;

    @Autowired
    TrainTicketRepository trainTicketRepository;

    @Override
    @Transactional
    public Map<String, String> createNewTrain(CreateTrain createTrain) {
        Train train = new Train(createTrain);
        log.info("saving new train info");
        this.trainRepository.save(train);
        log.info("new train info successfully saving");
        int seq = 1;
        for (CreateStops n : createTrain.getStops()) {
            TrainStop trainStop = new TrainStop(n, seq, train);
            trainStopRepository.save(trainStop);
            seq++;
        }
        log.info("train stop informations saved");
        return domainService.returnResult(train.getUuid());
    }

    @Override
    public Map<String, String> createNewTicket(CreateTicket createTicket) {
        Train train = this.trainRepository.findByTrainNo(createTicket.getTrainNo());
        TrainTicket trainTicket = new TrainTicket(createTicket, train, outboundService.getTicketPrice());
        this.trainTicketRepository.save(trainTicket);
        log.info("train ticket created successfully");
        return domainService.returnResult(trainTicket.getTrainUuid());
    }
}
