package org.example.schoolmanagement.dto;

import java.math.BigDecimal;

import org.example.schoolmanagement.enums.Role;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {
    private Integer id; 
    private String username;
    private String email;
    private String phone;
    private String address;
    private Boolean isActive;
    private Role role;
    private BigDecimal salary;
    private String department;
}