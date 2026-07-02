package com.tracking.api_tracking.response;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T> {
    private boolean status;
    private String message;
    private T data;

}
