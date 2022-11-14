package com.example.springhexpractice.serivce.commandservice;

import com.example.springhexpractice.controller.dto.request.CreateTicket;
import com.example.springhexpractice.controller.dto.request.CreateTrain;

import java.util.Map;

public interface CommandService {
    Map<String, String> createNewTrain(CreateTrain createTrain);
    Map<String, String> createNewTicket(CreateTicket createTicket);
}
