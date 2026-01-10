package org.example.schoolmanagement.dto;
import java.math.BigDecimal;

import org.example.schoolmanagement.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateEmployeeRequest {

      private String username;     // optional
    private String email;        // optional
    private String phone;        // optional
    private String address;      // optional
    private Boolean isActive;    // optional

    // Employee info
    private BigDecimal salary;   // optional
    private String department;   
}