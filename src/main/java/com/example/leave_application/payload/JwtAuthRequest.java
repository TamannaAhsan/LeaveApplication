package com.example.leave_application.payload;

import lombok.Data;

@Data
public class JwtAuthRequest {

    private String username;
    private String password;
}
