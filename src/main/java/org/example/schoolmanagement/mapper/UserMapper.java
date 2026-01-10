package org.example.schoolmanagement.mapper;

import org.example.schoolmanagement.dto.UserDTO;
import org.example.schoolmanagement.model.User;

public class UserMapper {
   
    public static UserDTO toDto(User model){
        if (model == null) throw new RuntimeException("invalid user entity") ;
         
        UserDTO dto=new UserDTO();
        
        dto.setId(model.getId());
        dto.setUsername(model.getUsername());
        dto.setEmail(model.getEmail());
        dto.setPhone(model.getPhone());
        dto.setAddress(model.getAddress());
        dto.setIsActive(model.getIsActive());
        dto.setRole(model.getRole());
        return dto;
  
    }
}
