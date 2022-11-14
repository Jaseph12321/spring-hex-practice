package com.example.springhexpractice.entity.errorMessage;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CheckErrorMessage {
    private String code;
    private String message;
    private String field="";

    public CheckErrorMessage(String code, String message){
        this.code = code;
        this.message = message;
    }
}
