package com.example.springhexpractice.iface.rest.dto.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StopTrainInformation {
    private int trainNo;
    private String trainKind;

}
