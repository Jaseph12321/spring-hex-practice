package com.example.springhexpractice.config.exception;

import com.example.springhexpractice.infra.utils.ErrorInfo;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
public class CheckErrorException extends Exception {
    private String error = "VALIDATE_FAILED";
    private List<ErrorInfo> errorInfo;


    public CheckErrorException(String error,List<ErrorInfo> errorInfo) {
        this.error = error;
        this.errorInfo = errorInfo;
    }

    public CheckErrorException(List<ErrorInfo> errorInfo) {
        this.errorInfo = errorInfo;
    }

}
