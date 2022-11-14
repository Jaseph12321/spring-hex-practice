package com.example.springhexpractice.serivce.domainservice;


import com.example.springhexpractice.controller.dto.request.CheckStatus;
import com.example.springhexpractice.controller.dto.request.CreateStops;
import com.example.springhexpractice.controller.dto.request.CreateTicket;
import com.example.springhexpractice.controller.dto.request.CreateTrain;
import com.example.springhexpractice.controller.dto.response.StopTrainInformation;
import com.example.springhexpractice.controller.dto.response.StopsResponse;
import com.example.springhexpractice.controller.dto.response.TrainStopInformation;
import com.example.springhexpractice.entity.Train;
import com.example.springhexpractice.entity.TrainStop;
import com.example.springhexpractice.entity.errorMessage.CheckErrorMessage;
import com.example.springhexpractice.exception.CheckErrorException;
import com.example.springhexpractice.exception.DataNotFoundException;
import com.example.springhexpractice.methods.NameAndKind;
import com.example.springhexpractice.repository.TrainRepository;
import com.example.springhexpractice.repository.TrainStopRepository;
import com.example.springhexpractice.serivce.applicationservice.PriceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Slf4j
public class DomainService {
    NameAndKind nameAndKind;

    @Autowired
    RestTemplate restTemplate;
    @Autowired
    TrainRepository trainRepository;

    @Autowired
    TrainStopRepository trainStopRepository;

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
        List<Map<String,Object>> mapList = this.trainStopRepository.findTrainNoAndTrainKind(trainStop);

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
