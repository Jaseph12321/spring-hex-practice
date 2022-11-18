package com.example.springhexpractice.serivce.query;

import com.example.springhexpractice.iface.rest.dto.response.StopTrainInformation;
import com.example.springhexpractice.iface.rest.dto.response.TrainStopInformation;
import com.example.springhexpractice.config.exception.DataNotFoundException;
import com.example.springhexpractice.serivce.ApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("QueryService")
@Slf4j
public class QueryServiceImpl implements QueryService {
    @Autowired
    ApplicationService applicationService;


    @Override
    //查詢車次的所有停靠站資訊
    public TrainStopInformation getSpecificTrainInformation(int trainNo) throws DataNotFoundException {
        return applicationService.specificTrainStopInformation(trainNo);
    }

    @Override
    //查詢所有停靠台北站的車次
    public List<StopTrainInformation> getSpecificStopInformation(String trainStop) {
        return applicationService.specificStopInformation(trainStop);
    }
}
