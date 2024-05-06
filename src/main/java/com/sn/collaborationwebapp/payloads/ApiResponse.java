package com.sn.collaborationwebapp.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.ObjectError;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ApiResponse {
    private String message;
    private boolean success;

    // Constructors
    public ApiResponse(String message, boolean success) {
        this.message = message;
        this.success = success;
    }

}
