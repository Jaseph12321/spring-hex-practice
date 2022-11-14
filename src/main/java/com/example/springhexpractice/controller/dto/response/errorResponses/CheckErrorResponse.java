package com.example.springhexpractice.controller.dto.response.errorResponses;

import com.example.springhexpractice.entity.errorMessage.CheckErrorMessage;
import com.example.springhexpractice.exception.CheckErrorException;
import com.example.springhexpractice.exception.ParamInvalidException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CheckErrorResponse {

    private String error = "VALIDATE_FAILED";
    private List<Map<String, String>> checkErrors;

    public CheckErrorResponse(CheckErrorException e) {

        Map<String, String> map = new LinkedHashMap<>();
        checkErrors = new ArrayList<>();
        for (CheckErrorMessage c : e.getErrMessages()) {
            map.put("code", c.getCode());
            map.put("message", c.getMessage());
            if (!Objects.equals(c.getField(), ""))
                map.put("field", c.getField());
            checkErrors.add(map);
        }
    }
}
