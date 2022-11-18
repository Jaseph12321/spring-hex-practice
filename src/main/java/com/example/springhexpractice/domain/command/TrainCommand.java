package com.example.springhexpractice.domain.command;

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
public class TrainCommand {

    @JsonProperty("train_no")
    private int trainNo;
    @JsonProperty("train_kind")
    private String trainKind;
    private List<StopsCommand> stops;
}
