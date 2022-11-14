package com.example.springhexpractice.controller.dto.request;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CheckStatus {
    private int id;
    private TagStatus category;
    private String name;
    private List<String> photoUrls;
    private List<TagStatus> tags;
    private String status;
}