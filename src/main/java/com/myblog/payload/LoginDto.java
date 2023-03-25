package com.myblog.payload;//99th STEP

import lombok.Data;

@Data
public class LoginDto {
    private String usernameOrEmail;
    private String password;
}

