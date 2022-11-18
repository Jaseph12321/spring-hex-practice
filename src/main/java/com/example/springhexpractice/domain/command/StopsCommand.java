package com.example.springhexpractice.domain.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class StopsCommand {
    private String stop_name;
    private LocalTime stop_time;
}
