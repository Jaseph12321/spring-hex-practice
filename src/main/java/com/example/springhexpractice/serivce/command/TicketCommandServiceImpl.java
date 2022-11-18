package com.example.springhexpractice.serivce.command;

import com.example.springhexpractice.domain.command.TicketCommand;
import com.example.springhexpractice.domain.foo.aggregate.service.DomainService;
import com.example.springhexpractice.iface.rest.dto.request.CreateTicket;
import com.example.springhexpractice.domain.foo.aggregate.entity.Train;
import com.example.springhexpractice.domain.foo.aggregate.entity.TrainTicket;
import com.example.springhexpractice.config.exception.CheckErrorException;
import com.example.springhexpractice.config.exception.DataNotFoundException;
import com.example.springhexpractice.infra.repository.TrainRepository;
import com.example.springhexpractice.infra.repository.TrainTicketRepository;
import com.example.springhexpractice.serivce.ApplicationService;
import com.example.springhexpractice.infra.outbound.OutboundService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("TicketCommandService")
@Slf4j
public class TicketCommandServiceImpl implements TicketCommandService {

    @Autowired
    TrainRepository trainRepository;

    @Autowired
    ApplicationService applicationService;

    @Autowired
    OutboundService outboundService;

    @Autowired
    TrainTicketRepository trainTicketRepository;

    @Autowired
    @Qualifier("ticketDomainService")
    DomainService<TicketCommand> ticketDomainService;




    @Override
    public Map<String, String> createNewTicket(TicketCommand ticketCommand) throws CheckErrorException, DataNotFoundException {
        Train train = this.trainRepository.findByTrainNo(ticketCommand.getTrainNo());
        log.info("go into validation phase");
        ticketDomainService.validation(ticketCommand);
        log.info("validation successful");
        TrainTicket trainTicket = TrainTicket.create(ticketCommand, train, outboundService.getTicketPrice());
        this.trainTicketRepository.save(trainTicket);
        log.info("train ticket created successfully");
        return applicationService.returnResult(trainTicket.getTrainUuid());
    }
}
