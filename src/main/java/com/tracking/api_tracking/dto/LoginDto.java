package com.tracking.api_tracking.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
public class LoginDto {
    @NotBlank(message = "Username cannot be left blank")
    private String username;

    @NotBlank(message = "Password cannot be left blank")
    @Size(max = 225 , min =  3 , message = "Password must not exceed 225 characters.")

    private String password;
}
