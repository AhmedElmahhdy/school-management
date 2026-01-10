package org.example.schoolmanagement.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.example.schoolmanagement.dto.*;
import org.example.schoolmanagement.service.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;
    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

    // ==========================
    // Register generic user
    // ==========================
    @PostMapping("/register")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<RegisterResponse> register(@Valid @RequestBody RegisterRequest request) {
        logger.info("Registering user: {}", request.getUsername());
        RegisterResponse response = adminService.register(request);
        logger.info("User registered with id: {}", response.getUserId());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // ==========================
    // List Employees (with pagination)
    // ==========================
    @GetMapping("/employees")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<BaseResponse<List<EmployeeDTO>>> getAllEmployees(
            @RequestParam(defaultValue = "1") @Min(1) Integer page,
            @RequestParam(defaultValue = "10") @Min(1) Integer size) {

        logger.info("Fetching employees: page={} , size={}", page, size);

        size = Math.min(size, 50); // max 50 per page

        List<EmployeeDTO> employees = adminService.selectAllEmployees(page, size);

        BaseResponse<List<EmployeeDTO>> response = new BaseResponse<>();
        response.setStatusCode(HttpStatus.OK.value());
        response.setStatusMessage("success");
        response.setData(employees);

        logger.info("Fetched {} employees", employees.size());
        return ResponseEntity.ok(response);
    }

    // ==========================
    // Update Employee (User + Employee)
    // ==========================
    @PutMapping("/employees/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<BaseResponse<EmployeeDTO>> updateEmployee(
            @PathVariable int id,
            @Valid @RequestBody UpdateEmployeeRequest request) {

        logger.info("Updating employee with id: {}", id);

        EmployeeDTO updatedEmployee = adminService.updateEmployee(id, request);

        BaseResponse<EmployeeDTO> response = new BaseResponse<>();
        response.setStatusCode(HttpStatus.OK.value());
        response.setStatusMessage("success");
        response.setData(updatedEmployee);

        logger.info("Updated employee with id: {}", id);
        return ResponseEntity.ok(response);
    }

    // ==========================
    // Add Employee (User + Employee)
    // ==========================
    @PostMapping("/employees")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<RegisterResponse> addEmployee(@Valid @RequestBody EmployeeRegisterRequest request) {
        logger.info("Adding employee: {}", request.getUsername());

        RegisterResponse response = adminService.addEmployee(request);

        logger.info("Employee registered with id: {}", response.getUserId());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
