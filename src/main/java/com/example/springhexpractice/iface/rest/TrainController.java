package com.example.springhexpractice.iface.rest;


import com.example.springhexpractice.domain.command.TicketCommand;
import com.example.springhexpractice.domain.command.TrainCommand;
import com.example.springhexpractice.domain.foo.aggregate.entity.Train;
import com.example.springhexpractice.iface.rest.dto.request.CreateTicket;
import com.example.springhexpractice.iface.rest.dto.request.CreateTrain;
import com.example.springhexpractice.iface.rest.dto.response.StopTrainInformation;
import com.example.springhexpractice.iface.rest.dto.response.TrainStopInformation;
import com.example.springhexpractice.config.exception.CheckErrorException;
import com.example.springhexpractice.config.exception.DataNotFoundException;
import com.example.springhexpractice.config.exception.UrlVarException;
import com.example.springhexpractice.iface.rest.dto.response.UuidResponse;
import com.example.springhexpractice.infra.repository.TrainRepository;
import com.example.springhexpractice.serivce.command.TicketCommandService;
import com.example.springhexpractice.serivce.command.TrainCommandService;
import com.example.springhexpractice.serivce.query.QueryService;
import com.example.springhexpractice.infra.utils.ErrorInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Map;

@RestController
@Validated
@Slf4j
public class TrainController {

    @Autowired
    @Qualifier("QueryService")
    QueryService queryService;

    @Autowired
    @Qualifier("TrainCommandService")
    TrainCommandService commandService;

    @Autowired
    @Qualifier("TicketCommandService")
    TicketCommandService ticketCommandService;

    ObjectMapper objectMapper = new ObjectMapper();



    //???????????????REST API????????????????????????223??????????????????????????????
    @GetMapping("/train/{train_no}/stops")
    public TrainStopInformation getSpecificTrainStops(@PathVariable String train_no) throws DataNotFoundException {
        try {
            if (Integer.parseInt(train_no) < 0 || Integer.parseInt(train_no) == Double.parseDouble(train_no)) {
                throw new NumberFormatException();
            }
            return this.queryService.getSpecificTrainInformation(Integer.parseInt(train_no));
        } catch (NumberFormatException e) {
            throw new NumberFormatException();
        }

    }


    // ???????????????REST API????????????????????????????????????????????????????????????????????????
    @GetMapping("/train")
    public List<StopTrainInformation> getAllNoByStop(@RequestParam String via) throws UrlVarException {
        if (via == null || via.equals(""))
            throw new UrlVarException(ErrorInfo.ViaInvalid.getMessage(), ErrorInfo.ViaInvalid.getField());
        System.out.println(via);
        return this.queryService.getSpecificStopInformation(via);
    }


    //???????????????REST API????????????????????????369??????
    @PostMapping("/train")
    public UuidResponse InsertTrain(@RequestBody @Valid CreateTrain createTrain) throws DateTimeParseException, DataNotFoundException, CheckErrorException {
         TrainCommand trainCommand = objectMapper.convertValue(createTrain, TrainCommand.class);
        return objectMapper.convertValue(this.commandService.createNewTrain(trainCommand).get("uuid"), UuidResponse.class);
    }



    //???????????????REST API????????????????????????1002???????????????
    @PostMapping("/ticket")
    public UuidResponse InsertTicket(@RequestBody @Valid CreateTicket createTicket) throws CheckErrorException, DataNotFoundException {
        log.info("get into ticket validation");
        TicketCommand ticketCommand = objectMapper.convertValue(createTicket, TicketCommand.class);
        return objectMapper.convertValue(this.ticketCommandService.createNewTicket(ticketCommand).get("uuid"), UuidResponse.class);
    }
}
