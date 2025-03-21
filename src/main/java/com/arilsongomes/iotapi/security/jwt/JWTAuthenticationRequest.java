package com.arilsongomes.iotapi.security.jwt;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class JWTAuthenticationRequest {
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String password;
}
