package com.example.springhexpractice.serivce.outboundservice;

import com.example.springhexpractice.controller.dto.request.CheckStatus;
import com.example.springhexpractice.controller.dto.request.CreateTrain;
import com.example.springhexpractice.exception.DataNotFoundException;
import com.example.springhexpractice.serivce.applicationservice.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OutboundService {


    @Autowired
    private RestTemplate restTemplate;



    public void checkTrainStatus(CreateTrain createTrain) throws DataNotFoundException {
        String url = "https://petstore.swagger.io/v2/pet/" + createTrain.getTrainNo();
        System.out.println(url);

        ResponseEntity<CheckStatus> response = restTemplate.getForEntity(url, CheckStatus.class);
        int code = response.getStatusCodeValue();
        if (code == 200) {
            CheckStatus check = response.getBody();
            if (!check.getStatus().equals("available"))
                throw new DataNotFoundException("train is not available");
        }
    }

    // 取得票價
    public Double getTicketPrice() {

        String url = "https://petstore.swagger.io/v2/store/inventory";

        ResponseEntity<PriceService> response = restTemplate.getForEntity(url, PriceService.class);

        return response.getBody().getSold();
    }
}
