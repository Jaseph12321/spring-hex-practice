package com.example.springhexpractice.serivce.queryservice;

import com.example.springhexpractice.controller.dto.response.StopTrainInformation;
import com.example.springhexpractice.controller.dto.response.TrainStopInformation;
import com.example.springhexpractice.exception.DataNotFoundException;

import java.util.List;

public interface QueryService {

    TrainStopInformation getSpecificTrainInformation(int trainNo) throws DataNotFoundException;
    List<StopTrainInformation> getSpecificStopInformation(String trainStop);
}
