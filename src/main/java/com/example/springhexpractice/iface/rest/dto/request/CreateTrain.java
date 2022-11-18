package com.example.springhexpractice.iface.rest.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateTrain {

    @NotNull(message = "車次不可為空")
    @JsonProperty("train_no")
    private int trainNo;
    @NotBlank(message = "車種不可為空")
    @JsonProperty("train_kind")
    private String trainKind;
    @Valid
    @NotEmpty(message = "停靠站不可為空")
    private List<CreateStops> stops;
}
