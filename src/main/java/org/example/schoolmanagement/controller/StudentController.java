package org.example.schoolmanagement.controller;

import org.example.schoolmanagement.dto.BaseResponse;
import org.example.schoolmanagement.dto.StudentDto;
import org.example.schoolmanagement.dto.UpdateStudentRequest;
import org.example.schoolmanagement.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@RestController
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentController {
   
    private final StudentService studentService;
    
    @PostMapping("/add-student")
   // @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<BaseResponse<StudentDto>> addStudent(@Valid @RequestBody StudentDto request){

        StudentDto createdStudent = studentService.addStudent(request);

        BaseResponse<StudentDto> response = new BaseResponse<>();
        response.setStatusCode(HttpStatus.OK.value());
        response.setStatusMessage("Student added successfully");
        response.setData(createdStudent);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/get-all-students")
    public ResponseEntity<BaseResponse<List<StudentDto>>> getAllStudents(){
        List<StudentDto> students = studentService.getAllStudents();

        BaseResponse<List<StudentDto>> response = new BaseResponse<>();
        response.setStatusCode(HttpStatus.OK.value());
        response.setStatusMessage("Students retrieved successfully");
        response.setData(students);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/get-students-by-parent-phone")
    public ResponseEntity<BaseResponse<List<StudentDto>>> getStudentsByParentPhone(
            @RequestParam("phone") String parentPhone){
        
        List<StudentDto> students = studentService.getStudentsByParentPhone(parentPhone);

        BaseResponse<List<StudentDto>> response = new BaseResponse<>();
        
        if (students.isEmpty()) {
            response.setStatusCode(HttpStatus.NOT_FOUND.value());
            response.setStatusMessage("No students found for the provided parent phone number");
            response.setData(null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        
        response.setStatusCode(HttpStatus.OK.value());
        response.setStatusMessage("Students retrieved successfully");
        response.setData(students);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete-student/{id}")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<BaseResponse<String>> deleteStudent(@PathVariable("id") int id){
        
        studentService.deleteStudent(id);

        BaseResponse<String> response = new BaseResponse<>();
        response.setStatusCode(HttpStatus.OK.value());
        response.setStatusMessage("Student deleted successfully");
        response.setData(null);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/update-student/{id}")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<BaseResponse<StudentDto>> updateStudent(
            @PathVariable("id") int id,
            @RequestBody UpdateStudentRequest request){
        
        StudentDto updatedStudent = studentService.updateStudent(id, request);

        BaseResponse<StudentDto> response = new BaseResponse<>();
        response.setStatusCode(HttpStatus.OK.value());
        response.setStatusMessage("Student updated successfully");
        response.setData(updatedStudent);

        return ResponseEntity.ok(response);
    }
}
