package com.tracking.api_tracking.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BranchDto {
    @NotBlank(message = "The name cannot be left blank")
    @Size(max = 225 , min =  3 , message = "Names must not exceed 225 characters.")
    private String name;
    @NotBlank(message = "The location cannot be left blank")
    @Size(max = 225 , min =  3 , message = "location must not exceed 225 characters.")
    private String location;
    @NotBlank(message = "The code cannot be left blank")
    @Size(max = 225 , min =  3 , message = "code must not exceed 225 characters.")
    private String code;
    @NotBlank(message = "The phone cannot be left blank")
    @Size(max = 225 , min =  3 , message = "phone must not exceed 225 characters.")
    private String phone;
    @NotBlank(message = "The email cannot be left blank")
    @Email(message = "Email is not in the correct format." )
    @Size(max = 225 , min =  3 , message = "email must not exceed 225 characters.")
    private String email;
    @NotBlank(message = "Timezone cannot be left blank")
    @Size(max = 225, message = "Timezone  must not exceed 225 characters.")
    private String timezone;
    private Integer isActive = 1;
    private Integer gpsRadius = 1;
}
