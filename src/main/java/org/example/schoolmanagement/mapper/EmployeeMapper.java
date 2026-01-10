package org.example.schoolmanagement.mapper;

import org.example.schoolmanagement.dto.EmployeeDTO;
import org.example.schoolmanagement.model.Employee;
import org.example.schoolmanagement.model.User;

public class EmployeeMapper {

    public static EmployeeDTO toDto(User user, Employee employee) {
        if (user == null || employee == null) return null;

        EmployeeDTO dto = new EmployeeDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setPhone(user.getPhone());
        dto.setAddress(user.getAddress());
        dto.setIsActive(user.getIsActive());
        dto.setRole(user.getRole());

        dto.setSalary(employee.getSalary());
        dto.setDepartment(employee.getDepartment());

        return dto;
    }
}


