package com.example.springhexpractice.serivce.queryservice;

import com.example.springhexpractice.controller.dto.response.StopTrainInformation;
import com.example.springhexpractice.controller.dto.response.TrainStopInformation;
import com.example.springhexpractice.exception.DataNotFoundException;
import com.example.springhexpractice.serivce.domainservice.DomainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("QueryService")
@Slf4j
public class QueryServiceImpl implements QueryService {
    @Autowired
    DomainService domainService;


    @Override
    //查詢車次的所有停靠站資訊
    public TrainStopInformation getSpecificTrainInformation(int trainNo) throws DataNotFoundException {
        return domainService.specificTrainStopInformation(trainNo);
    }

    @Override
    //查詢所有停靠台北站的車次
    public List<StopTrainInformation> getSpecificStopInformation(String trainStop) {
        return domainService.specificStopInformation(trainStop);
    }
}
