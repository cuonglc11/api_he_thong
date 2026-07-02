package com.tracking.api_tracking.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
public class ProvisionManagerDto {
    @NotBlank(message = "Username cannot be left blank.")
    private String userName;

    @NotBlank(message = "Password cannot be left blank.")
    @Size(max = 225, min = 3, message = "Password must not exceed 225 characters.")
    private String password;

    // --- Employee Information ---
    @NotBlank(message = "Code cannot be left blank.")
    private String code;

    @NotBlank(message = "FullName cannot be left blank.")
    private String fullName;

    @NotBlank(message = "Phone number cannot be left blank.")
    @Pattern(regexp = "^(0|\\+84)[3|5|7|8|9][0-9]{8}$", message = "Invalid Vietnamese phone number format.")
    private String phone;

    @NotBlank(message = "Email cannot be left blank.")
    @Email(message = "Invalid email format.")
    private String email;

    // Optional fields (Default values will be used if not provided by the client)
    private int contractType = 1;
    private int maxWeklyHrs = 40;
    private int leaveQuota = 12;
}
