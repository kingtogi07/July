package com.example.July.dto;

import lombok.Data;

@Data
public class MemberResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String address;
    private String joinedDate;
}
