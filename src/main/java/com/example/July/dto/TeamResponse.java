package com.example.July.dto;

import lombok.Data;

import java.util.List;

@Data
public class TeamResponse {
    private Long id;
    private String name;
    private String location;
    private String foundedDate;
    private List<MemberResponse> members;
}
