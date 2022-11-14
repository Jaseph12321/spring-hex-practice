package com.example.springhexpractice.controller;


import com.example.springhexpractice.controller.dto.request.CreateTicket;
import com.example.springhexpractice.controller.dto.request.CreateTrain;
import com.example.springhexpractice.controller.dto.response.StopTrainInformation;
import com.example.springhexpractice.controller.dto.response.TrainStopInformation;
import com.example.springhexpractice.exception.CheckErrorException;
import com.example.springhexpractice.exception.DataNotFoundException;
import com.example.springhexpractice.exception.UrlVarException;
import com.example.springhexpractice.serivce.applicationservice.ApplicationService;
import com.example.springhexpractice.serivce.commandservice.CommandService;
import com.example.springhexpractice.serivce.queryservice.QueryService;
import lombok.extern.slf4j.Slf4j;
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
    @Qualifier("CommandService")
    CommandService commandService;

    @Autowired
    ApplicationService applicationService;


    //請提供一支REST API，查詢諾亞方舟號223車次的所有停靠站資訊
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


    // 請提供一支REST API，查詢所有停靠台北站的車次，並依停靠時間先後排列
    @GetMapping("/train")
    public List<StopTrainInformation> getAllNoByStop(@RequestParam String via) throws UrlVarException {
        if (via == null || via.equals(""))
            throw new UrlVarException("Required String parameter 'via' is not present", "via");
        System.out.println(via);
        return this.queryService.getSpecificStopInformation(via);
    }


    //請提供一支REST API，新增諾亞方舟號369車次
    @PostMapping("/train")
    public Map<String, String> InsertTrain(@RequestBody @Valid CreateTrain createTrain) throws DateTimeParseException, DataNotFoundException, CheckErrorException {
        applicationService.TrainValidation(createTrain);
        return this.commandService.createNewTrain(createTrain);
    }

    //請提供一支REST API，新增霍格華茲號1002車次的車票
    @PostMapping("/ticket")
    public Map<String, String> InsertTicket(@RequestBody @Valid CreateTicket createTicket) throws CheckErrorException {
        log.info("get into ticket validation");
        applicationService.TicketValidation(createTicket);
        return this.commandService.createNewTicket(createTicket);
    }
}
