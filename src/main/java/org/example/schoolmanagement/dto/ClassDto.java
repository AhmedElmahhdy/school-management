package org.example.schoolmanagement.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Setter
@NoArgsConstructor
@Getter
public class ClassDto {
   @NotNull(message = "Teacher ID is required")
    private Integer teacher_id;

    @NotBlank(message = "Class name is required")
    private String name;

  
}
