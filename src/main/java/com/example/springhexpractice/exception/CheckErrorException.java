package com.example.springhexpractice.exception;

import com.example.springhexpractice.entity.errorMessage.CheckErrorMessage;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
public class CheckErrorException extends Exception {
    private String error;
    private List<CheckErrorMessage> errMessages;


    public CheckErrorException(String error, List<CheckErrorMessage> errMessages) {
        System.out.println(1);
        this.error = error;
        this.errMessages = errMessages;
    }
}
