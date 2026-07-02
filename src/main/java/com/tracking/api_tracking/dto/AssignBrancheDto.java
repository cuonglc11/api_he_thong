package com.tracking.api_tracking.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AssignBrancheDto {
    @NotNull(message = "Branch ID cannot be null.")
    private Long branchId;
    @NotNull(message = "The Start Date must not be left blank.")
    @FutureOrPresent(message = "The inauguration day must be today or a date in the future.")
    private LocalDate startDate;
}
