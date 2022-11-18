package com.example.springhexpractice.iface.rest.dto.response.errorResponses;

import com.example.springhexpractice.config.exception.CheckErrorException;
import com.example.springhexpractice.infra.utils.ErrorInfo;
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
        for (ErrorInfo c: e.getErrorInfo()) {
            map.put("code", c.getCode());
            map.put("message", c.getMessage());
            if (!"".equals(c.getField()))
                map.put("field", c.getField());
            checkErrors.add(map);
        }
    }
}
