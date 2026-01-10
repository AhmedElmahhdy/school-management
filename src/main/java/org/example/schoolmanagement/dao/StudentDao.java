package org.example.schoolmanagement.dao;

import org.example.schoolmanagement.model.Student;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

@RegisterBeanMapper(Student.class)
public interface StudentDao {
  
    @SqlUpdate("INSERT INTO students(full_name, class_id,student_parent) VALUES (:full_name, :class_id, :student_parent)")
    @GetGeneratedKeys
    int insert(@BindBean Student student);
}
