package org.example.schoolmanagement.dao;

import org.example.schoolmanagement.model.Student;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;
import java.util.Optional;

@RegisterBeanMapper(Student.class)
public interface StudentDao {
  
    @SqlUpdate("INSERT INTO students(full_name, class_id,student_parent) VALUES (:full_name, :class_id, :student_parent)")
    @GetGeneratedKeys
    int insert(@BindBean Student student);

    @SqlQuery("SELECT * FROM students ORDER BY id ASC")
    List<Student> findAll();

    @SqlQuery("SELECT s.* FROM students s " +
              "INNER JOIN users u ON s.student_parent = u.id " +
              "WHERE u.phone = :phone ORDER BY s.id ASC")
    List<Student> findByParentPhone(@Bind("phone") String phone);

    @SqlQuery("SELECT * FROM students WHERE id = :id")
    Optional<Student> findById(@Bind("id") int id);

    @SqlUpdate("DELETE FROM students WHERE id = :id")
    int delete(@Bind("id") int id);

    @SqlUpdate("UPDATE students SET " +
               "full_name = COALESCE(:full_name, full_name), " +
               "class_id = COALESCE(:class_id, class_id), " +
               "student_parent = COALESCE(:student_parent, student_parent) " +
               "WHERE id = :id")
    int update(@Bind("id") int id, 
               @Bind("full_name") String fullName,
               @Bind("class_id") Integer classId,
               @Bind("student_parent") Integer studentParent);
}
