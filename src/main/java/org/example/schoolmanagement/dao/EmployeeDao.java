package org.example.schoolmanagement.dao;

import java.util.List;
import java.util.Optional;

import org.example.schoolmanagement.dto.UpdateEmployeeRequest;
import org.example.schoolmanagement.model.Employee;
import org.example.schoolmanagement.model.User;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;


@RegisterBeanMapper(Employee.class)
public interface EmployeeDao {

    // Insert Employee data (salary + department) only
    @SqlUpdate("INSERT INTO employee(user_id, salary, department) " +
               "VALUES (:userId, :salary, :department)")
    int insert(@BindBean Employee employee);

    // Update Employee data (salary + department)
    @SqlUpdate("UPDATE employee SET " +
               "salary = :salary, " +
               "department = :department " +
               "WHERE user_id = :userId")
    int update(@BindBean Employee employee);

    // Delete Employee
    @SqlUpdate("DELETE FROM employee WHERE user_id = :userId")
    int delete(@Bind("userId") Integer userId);

    // Find by User ID
    @SqlQuery("SELECT * FROM employee WHERE user_id = :userId")
    Optional<Employee> findByUserId(@Bind("userId") Integer userId);

    // Find all Employees (with pagination)
    @SqlQuery("SELECT * FROM employee ORDER BY user_id ASC LIMIT :size OFFSET :offset")
    List<Employee> selectEmployees(@Bind("offset") int offset, @Bind("size") int size);
}
