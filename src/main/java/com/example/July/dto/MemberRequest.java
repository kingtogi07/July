package com.example.July.dto;

import lombok.Data;

@Data
public class MemberRequest {
    private String firstName;
    private String lastName;
    private String address;
    private String joinedDate;
    private Long teamId;
}
