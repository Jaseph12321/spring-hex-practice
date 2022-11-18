package com.example.springhexpractice.serivce;


import com.example.springhexpractice.iface.rest.dto.response.StopTrainInformation;
import com.example.springhexpractice.iface.rest.dto.response.StopsResponse;
import com.example.springhexpractice.iface.rest.dto.response.TrainStopInformation;
import com.example.springhexpractice.domain.foo.aggregate.entity.Train;
import com.example.springhexpractice.config.exception.DataNotFoundException;
import com.example.springhexpractice.infra.utils.NameAndKind;
import com.example.springhexpractice.infra.repository.TrainRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class ApplicationService {
    NameAndKind nameAndKind;
    @Autowired
    TrainRepository trainRepository;


    //methods



    public TrainStopInformation specificTrainStopInformation(int trainNo) throws DataNotFoundException {
        Train train = this.trainRepository.findByTrainNo(trainNo);

        TrainStopInformation trainStopInformation = new TrainStopInformation(train.getTrainNo(),nameAndKind.getName(train.getTrainKind()));
        if (this.trainRepository.findByTrainNoAndStopAndTime(trainNo).size() == 0) {
            throw new DataNotFoundException("車次不存在");
        }
        List<Map<String, Object>> mapList = this.trainRepository.findByTrainNoAndStopAndTime(trainNo);
        List<StopsResponse> stopsResponseList = new ArrayList<>();
        for (Map<String, Object> map : mapList) {
            stopsResponseList.add(new StopsResponse(map.get("NAME"), map.get("TIME")));
        }
        trainStopInformation.setStops(stopsResponseList);
        return trainStopInformation;
    }


    public List<StopTrainInformation> specificStopInformation(String trainStop) {
        List<StopTrainInformation> trains = new ArrayList<>();
        List<Map<String,Object>> mapList = this.trainRepository.findTrainNoAndTrainKind(trainStop);

        for (Map<String,Object> m : mapList) {
            StopTrainInformation stopTrainInformation = new StopTrainInformation(Integer.parseInt(m.get("TRAIN_NO").toString()),m.get("TRAIN_KIND").toString());
            trains.add(stopTrainInformation);
        }
        return trains;
    }

    // QueryServiceImpl 回傳資訊
    public Map<String, String> returnResult(String s) {
        Map<String, String> m = new HashMap<>();
        m.put("uuid", s);
        return m;
    }


}
