package org.example.schoolmanagement.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.example.schoolmanagement.dto.BaseResponse;
import org.example.schoolmanagement.dto.ClassDto;
import org.example.schoolmanagement.dto.UserDTO;
import org.example.schoolmanagement.service.ClassService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/class")
@RequiredArgsConstructor
public class ClassController {

    private final ClassService classService;

    @PostMapping("/add-class")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<BaseResponse<ClassDto>> addClass(@Valid @RequestBody ClassDto request) {
         
       ClassDto createdClass = classService.addClass(request);

        // Build response
        BaseResponse<ClassDto> response = new BaseResponse<>();
        response.setStatusCode(HttpStatus.OK.value());
        response.setStatusMessage("Class added successfully");
        response.setData(createdClass);

        // Return ResponseEntity with 201 CREATED
        return ResponseEntity.status(HttpStatus.CREATED).body(response);

       
    }
    
}
