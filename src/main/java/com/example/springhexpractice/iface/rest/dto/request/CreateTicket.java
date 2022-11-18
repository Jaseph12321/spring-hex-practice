package com.example.springhexpractice.iface.rest.dto.request;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateTicket {

    @NotNull(message = "車次不可為空")
    @JsonProperty("train_no")
    private int trainNo;
    @NotBlank(message = "上車站不可為空")
    @JsonProperty("from_stop")
    private String fromStop;
    @NotBlank(message = "下車站不可為空")
    @JsonProperty("to_stop")
    private String toStop;

    @NotEmpty(message = "日期不可為空")
    @Pattern(regexp = "((((19|20)\\d{2})-(0?[13578]|1[02])-(0?[1-9]|[12]\\d|3[01]))|(((19|20)\\d{2})-(0?[469]|11)-(0?[1-9]|[12]\\d|30))|(((19|20)\\d{2})-0?2-(0?[1-9]|1\\d|2[0-8]))|((((19|20)([13579][26]|[2468][048]|0[48]))|(2000))-0?2-(0?[1-9]|[12]\\d)))$"
            , message = "日期格式不正確 yyyy-mm-dd")
    @JsonProperty("take_date")
    private String takeDate;
}
