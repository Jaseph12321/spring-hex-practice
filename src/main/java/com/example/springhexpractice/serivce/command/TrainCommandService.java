package com.example.springhexpractice.serivce.command;

import com.example.springhexpractice.domain.command.TrainCommand;
import com.example.springhexpractice.iface.rest.dto.request.CreateTrain;
import com.example.springhexpractice.config.exception.CheckErrorException;
import com.example.springhexpractice.config.exception.DataNotFoundException;

import java.util.Map;

public interface TrainCommandService {
    Map<String, String> createNewTrain(TrainCommand trainCommand) throws DataNotFoundException, CheckErrorException;
}
