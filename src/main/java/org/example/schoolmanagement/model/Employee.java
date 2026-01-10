package org.example.schoolmanagement.model;

import java.math.BigDecimal;

import org.example.schoolmanagement.enums.Role;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Employee {
 private Integer userId;
private String department;
private BigDecimal salary;
}