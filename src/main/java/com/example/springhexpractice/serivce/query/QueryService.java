package com.example.springhexpractice.serivce.query;

import com.example.springhexpractice.iface.rest.dto.response.StopTrainInformation;
import com.example.springhexpractice.iface.rest.dto.response.TrainStopInformation;
import com.example.springhexpractice.config.exception.DataNotFoundException;

import java.util.List;

public interface QueryService {

    TrainStopInformation getSpecificTrainInformation(int trainNo) throws DataNotFoundException;
    List<StopTrainInformation> getSpecificStopInformation(String trainStop);
}
