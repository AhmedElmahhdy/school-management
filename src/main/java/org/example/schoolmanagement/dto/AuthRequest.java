package org.example.schoolmanagement.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AuthRequest {

    @NotBlank(message = "phoneNumber is required")
    @Size(min = 11, message = "phoneNumber must be 11")
    private String phoneNumber;

    @NotBlank(message = "password is required")
    @Size(min = 5, max = 64, message = "password must be between 8 and 64 characters")
    private String password;
}
