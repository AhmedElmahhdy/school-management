package org.example.schoolmanagement.controller;

import org.example.schoolmanagement.dto.BaseResponse;
import org.example.schoolmanagement.dto.StudentDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
public class Employee {
   
}
