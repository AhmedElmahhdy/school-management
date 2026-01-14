package org.example.schoolmanagement.dto;

import lombok.Data;

@Data
public class UpdateStudentRequest {
    
    private String full_name;        // optional
    private String parentPhone;      // optional
    private Integer class_id;       // optional
}
