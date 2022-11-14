package com.example.springhexpractice.controller.dto.response.errorResponses;

import com.example.springhexpractice.entity.errorMessage.FieldErrorMessage;
import com.example.springhexpractice.exception.UrlVarException;
import com.example.springhexpractice.exception.ParamInvalidException;
import lombok.*;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.time.format.DateTimeParseException;
import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ErrorResponse {
    private String error;
    private List<Map<String, Object>> fieldError;

    public ErrorResponse(NumberFormatException e) {

        this.fieldError = new ArrayList<>();

        Map<String, Object> map = new HashMap<>();
        FieldErrorMessage fieldErrorMessage = new FieldErrorMessage();
        map.put("code", fieldErrorMessage.getCode());


        map.put("message", fieldErrorMessage.getMessage());

        map.put("field", fieldErrorMessage.getField());

        fieldError.add(map);
        this.error = "Validate Failed";
    }

    public ErrorResponse(UrlVarException e) {
        this.fieldError = new ArrayList<>();

        Map<String, Object> map = new HashMap<>();

        map.put("message", e.getMessage());

        map.put("field", e.getField());

        fieldError.add(map);
        this.error = "Validate Failed";
    }

    public ErrorResponse(ParamInvalidException e) {
        this.fieldError = new ArrayList<>();
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("fieldErrors", e.getErrMessages());
        fieldError.add(map);
        this.error = e.getError();
    }

    public ErrorResponse(MethodArgumentNotValidException e) {
        this.fieldError = new ArrayList<>();
        this.error = "Valdate Failed";

        e.getBindingResult().getFieldErrors().stream().forEach(m -> {
            Map<String, Object> fieldmap = new HashMap<>();
            fieldmap.put("code", m.getCode());
            fieldmap.put("fields", m.getField());
            fieldmap.put("message", m.getDefaultMessage());
            fieldError.add(fieldmap);
        });
    }

    public ErrorResponse(DateTimeParseException e) {
        this.fieldError = new ArrayList<>();
        this.error = "Validate Failed";
        Map<String, Object> fieldmap = new HashMap<>();
        fieldmap.put("code", "time");
        fieldmap.put("message", "stop time is imvalid");
        fieldmap.put("fields", "stop_time");
        fieldError.add(fieldmap);

    }
}
