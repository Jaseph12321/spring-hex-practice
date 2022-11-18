package com.example.springhexpractice.serivce.command;

import com.example.springhexpractice.domain.command.TicketCommand;
import com.example.springhexpractice.iface.rest.dto.request.CreateTicket;
import com.example.springhexpractice.config.exception.CheckErrorException;
import com.example.springhexpractice.config.exception.DataNotFoundException;

import java.util.Map;

public interface TicketCommandService {

    Map<String, String> createNewTicket(TicketCommand ticketCommand) throws CheckErrorException, DataNotFoundException;
}
