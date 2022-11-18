package com.example.springhexpractice.infra.utils;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public enum ErrorInfo {


    ViaInvalid("", "Required String parameter 'via' is not present", "via"),
    //check errors
    TrainNoInvalid("Min", "車次必須為正整數", "trainNo"),
    TrainNotAvailable("TrainNotAvailable", "Train is not available"),
    TrainNoExists("TrainNoExists", "Train No is exists"),
    TrainKindInvalid("TrainKindInvalid", "Train Kind is invalid"),
    TrainStopsDuplicate("TrainStopsDuplicate", "Train Stops is duplicate"),
    TrainStopsNotSorted("TrainStopsNotSorted", "Train Stops is not sorted"),
    DatePatternInvalid("Pattern","日期格式不正確 yyyy-mm-dd","takeDate"),
    TicketStopsInvalid("TicketStopsInvalid","Ticket From & To is invalid");



    private  String code;
    private  String message;
    private String field;

    ErrorInfo(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
