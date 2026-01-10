package org.example.schoolmanagement.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.example.schoolmanagement.enums.Role;

@Getter
@Setter
@Data
public class RegisterRequest {
    private String username;
    private String password;
    private String email;
    private String phone;
    private String address;
    private Role role;

}
