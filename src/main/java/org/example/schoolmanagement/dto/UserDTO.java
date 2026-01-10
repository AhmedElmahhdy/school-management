package org.example.schoolmanagement.dto;

import org.example.schoolmanagement.enums.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO{
        Integer id;
        String username;
        String email;
        String phone;
        String address;
        Boolean isActive;
        Role role;
}
