package com.example.springhexpractice.controller.dto.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TrainStopInformation {

    private int trainNo;
    private String trainKind;
    private List<StopsResponse> stops;

    public TrainStopInformation(int trainNo, String name) {
        this.trainNo = trainNo;
        this.trainKind = name;
    }
}
