package com.example.springhexpractice.iface.rest.dto.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StopsResponse {
    private String stop_name;
    private LocalTime stop_time;

    public StopsResponse(Object stop_name, Object stop_time){
        this.stop_name =  stop_name.toString();
        this.stop_time = LocalTime.parse(stop_time.toString());
    }
}
