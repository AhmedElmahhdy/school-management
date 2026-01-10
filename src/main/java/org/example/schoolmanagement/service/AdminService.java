package org.example.schoolmanagement.service;

import lombok.RequiredArgsConstructor;
import org.example.schoolmanagement.dao.EmployeeDao;
import org.example.schoolmanagement.dao.UserDao;
import org.example.schoolmanagement.dto.*;
import org.example.schoolmanagement.mapper.EmployeeMapper;
import org.example.schoolmanagement.mapper.UserMapper;
import org.example.schoolmanagement.model.Employee;
import org.example.schoolmanagement.model.User;
import org.example.schoolmanagement.utils.BusinessException;
import org.example.schoolmanagement.utils.UserAlreadyExistsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AdminService {

    private final UserDao userDao;
    private final EmployeeDao employeeDao;
    private final PasswordEncoder passwordEncoder;

    // ==========================
    // Register generic user
    // ==========================
    public RegisterResponse register(RegisterRequest request) {
        if (userDao.findByUsername(request.getUsername()).isPresent()) {
            throw new UserAlreadyExistsException("Username already exists");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setAddress(request.getAddress());
        user.setRole(request.getRole());
        user.setIsActive(true);

        Integer id = userDao.save(user);

        return new RegisterResponse(id, "User registered successfully");
    }

    // ==========================
    // Add Employee (User + Employee)
    // ==========================
    @Transactional
    public RegisterResponse addEmployee(EmployeeRegisterRequest request) {

        // Check if username exists in USERS
        if (userDao.findByUsername(request.getUsername()).isPresent()) {
            throw new BusinessException(400,"Username already exists");
        }

        // 1️⃣ Insert into USERS
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setAddress(request.getAddress());
        user.setRole(request.getRole()); // EMPLOYEE role
        user.setIsActive(true);

        Integer userId = userDao.save(user);

        // 2️⃣ Insert into EMPLOYEES
        Employee employee = new Employee();
        employee.setUserId(userId);
        employee.setSalary(request.getSalary());
        employee.setDepartment(request.getDepartment());

        employeeDao.insert(employee);

        return new RegisterResponse(userId, "Employee registered successfully");
    }

    // ==========================
    // Update Employee (User + Employee)
    // ==========================
    @Transactional
    public EmployeeDTO updateEmployee(int id, UpdateEmployeeRequest request) {

        // Check username uniqueness
        if (request.getUsername() != null) {
            userDao.findByUsername(request.getUsername())
                    .filter(u -> !u.getId().equals(id))
                    .ifPresent(u -> { throw new UserAlreadyExistsException("Username already exists"); });
        }

        // 1️⃣ Update USERS
        int updatedUserRows = userDao.updateUser(id, request);
        if (updatedUserRows == 0) {
            throw new UsernameNotFoundException("User not found");
        }

        // 2️⃣ Update EMPLOYEES (salary/department)
        Employee employeeUpdate = new Employee();
        employeeUpdate.setUserId(id);
        employeeUpdate.setSalary(request.getSalary());
        employeeUpdate.setDepartment(request.getDepartment());

        employeeDao.update(employeeUpdate);

        User updatedUser = userDao.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        Employee employee = employeeDao.findByUserId(id)
                .orElse(null);

        return EmployeeMapper.toDto(updatedUser, employee);
    }

    // ==========================
    // List Employees (with User info)
    // ==========================
    public List<EmployeeDTO> selectAllEmployees(int page, int size) {
        int offset = (page - 1) * size;

        List<Employee> employees = employeeDao.selectEmployees(offset, size);

        return employees.stream()
                .map(emp -> {
                    User user = userDao.findById(emp.getUserId()).orElse(null);
                    return EmployeeMapper.toDto(user, emp);
                })
                .toList();
    }
}
