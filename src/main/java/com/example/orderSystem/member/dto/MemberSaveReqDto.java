package com.example.orderSystem.member.dto;

import lombok.Data;

@Data
public class MemberSaveReqDto {
    private String name;
    private String email;
    private String password;
    private String role;
}
