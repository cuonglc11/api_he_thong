package com.tracking.api_tracking.dto;

import jakarta.validation.constraints.Email;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BranchUpdateDto {
    private String name;
    private String location;
    private String code;
    private String phone;
    @Email(message = "Email is not in the correct format." )
    private String email;
    private String timezone;
    private Integer isActive = 1;
    private Integer gpsRadius = 1;
}
