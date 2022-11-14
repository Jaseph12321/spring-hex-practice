package com.example.springhexpractice.entity.errorMessage;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FieldErrorMessage {
    private String code = "min";
    private String message = "車次必須為正整數";
    private String field = "trainNo";

//    @Override
//    public String toString() {
//        return code+"/"+message+"/"+field;
//    }
}
