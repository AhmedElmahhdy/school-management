package org.example.schoolmanagement.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor
public class Student {
    
    private Integer id;

    private String full_name;

     private Integer student_parent;

    private Integer class_id;

    private Boolean isActive;
}
