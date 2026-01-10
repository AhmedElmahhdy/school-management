package org.example.schoolmanagement.controller;

import org.apache.coyote.BadRequestException;
import org.example.schoolmanagement.dto.BaseResponse;
import org.example.schoolmanagement.dto.StudentDto;
import org.example.schoolmanagement.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentController {
   
    private final StudentService studentService;
    
    @PostMapping("/add-student")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<BaseResponse<StudentDto>> addStudent(@Valid @RequestBody StudentDto request){

        StudentDto createdStudent = studentService.addStudent(request);

        BaseResponse<StudentDto> response = new BaseResponse<>();
        response.setStatusCode(HttpStatus.OK.value());
        response.setStatusMessage("Student added successfully");
        response.setData(createdStudent);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
