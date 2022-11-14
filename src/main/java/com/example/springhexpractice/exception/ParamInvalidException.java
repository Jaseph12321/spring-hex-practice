package com.example.springhexpractice.exception;

import com.example.springhexpractice.entity.errorMessage.FieldErrorMessage;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ParamInvalidException extends Exception {

    private String error;
    private List<FieldErrorMessage> errMessages;


    public ParamInvalidException(String error, List<FieldErrorMessage> errMessages) {
        this.error = error;
        this.errMessages = errMessages;
    }

}
