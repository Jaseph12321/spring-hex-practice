package com.example.springhexpractice.infra.outbound;

import com.example.springhexpractice.domain.command.TrainCommand;
import com.example.springhexpractice.iface.rest.dto.request.CreateTrain;
import com.example.springhexpractice.config.exception.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OutboundService {


    @Autowired
    private RestTemplate restTemplate;

    @Value("${ticket_price}")
    String ticketPriceUrl;
    @Value("${status}")
    String statusUrl;



    public void checkTrainStatus(TrainCommand trainCommand) throws DataNotFoundException {
        String url = statusUrl + trainCommand.getTrainNo();
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

        String url = ticketPriceUrl;

        ResponseEntity<PriceService> response = restTemplate.getForEntity(url, PriceService.class);

        return response.getBody().getSold();
    }
}
