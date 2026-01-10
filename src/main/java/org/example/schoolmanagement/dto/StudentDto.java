
package org.example.schoolmanagement.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StudentDto {

    private Integer id;

    @NotBlank(message = "Full name is required")
    private String full_name;


    @NotBlank(message = "parentPhone is required")
    private String parentPhone;

    @NotNull(message = "Class ID is required")
    private Integer class_id;
}

