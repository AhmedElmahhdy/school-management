package org.example.schoolmanagement.service;

import org.example.schoolmanagement.dao.ClassDao;
import org.example.schoolmanagement.dao.UserDao;
import org.example.schoolmanagement.dao.StudentDao;
import org.example.schoolmanagement.dto.StudentDto;
import org.example.schoolmanagement.dto.UpdateStudentRequest;
import org.example.schoolmanagement.model.Student;
import org.springframework.stereotype.Service;
import org.example.schoolmanagement.utils.BusinessException;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentService {
   
      private final StudentDao studentDao;
      private final ClassDao classDao;
      private final UserDao UserDao;
    
    public StudentDto addStudent(StudentDto request){
       if (classDao.findById(request.getClass_id()).isEmpty()) {
             throw new BusinessException(404,"Class ID does not exist");
       }

       Integer parentId = UserDao.findParentIdByPhone(request.getParentPhone());

      if (parentId == null) {
      throw new BusinessException(404,"Parent not found");
      }
      
      Student student=new Student();
      student.setClass_id(request.getClass_id());
      student.setFull_name(request.getFull_name());
      student.setStudent_parent(parentId);
       int id = studentDao.insert(student);

      request.setId(id);
      return request;
    }

    public List<StudentDto> getAllStudents(){
        List<Student> students = studentDao.findAll();
        
        return students.stream()
                .map(student -> {
                    StudentDto dto = new StudentDto();
                    dto.setId(student.getId());
                    dto.setFull_name(student.getFull_name());
                    dto.setClass_id(student.getClass_id());
                    
                    // Get parent phone if parent exists
                    if (student.getStudent_parent() != null) {
                        UserDao.findById(student.getStudent_parent())
                                .ifPresent(user -> dto.setParentPhone(user.getPhone()));
                    }
                    
                    return dto;
                })
                .collect(Collectors.toList());
    }

    public List<StudentDto> getStudentsByParentPhone(String parentPhone){
        List<Student> students = studentDao.findByParentPhone(parentPhone);
        
        return students.stream()
                .map(student -> {
                    StudentDto dto = new StudentDto();
                    dto.setId(student.getId());
                    dto.setFull_name(student.getFull_name());
                    dto.setClass_id(student.getClass_id());
                    dto.setParentPhone(parentPhone);
                    
                    return dto;
                })
                .collect(Collectors.toList());
    }

    public void deleteStudent(int id){
        // Check if student exists
        if (studentDao.findById(id).isEmpty()) {
            throw new BusinessException(404, "Student not found");
        }
        
        // Delete the student
        int deletedRows = studentDao.delete(id);
        
        if (deletedRows == 0) {
            throw new BusinessException(500, "Failed to delete student");
        }
    }

    public StudentDto updateStudent(int id, UpdateStudentRequest request){
        // Check if student exists
        if (studentDao.findById(id).isEmpty()) {
            throw new BusinessException(404, "Student not found");
        }
        
        // Validate class_id if provided
        if (request.getClass_id() != null) {
            if (classDao.findById(request.getClass_id()).isEmpty()) {
                throw new BusinessException(404, "Class ID does not exist");
            }
        }
        
        // Get parent ID if parent phone is provided
        Integer parentId = null;
        if (request.getParentPhone() != null && !request.getParentPhone().isEmpty()) {
            parentId = UserDao.findParentIdByPhone(request.getParentPhone());
            if (parentId == null) {
                throw new BusinessException(404, "Parent not found");
            }
        }
        // If parentPhone is not provided, parentId remains null, and COALESCE will keep existing value
        
        // Update the student
        int updatedRows = studentDao.update(
            id,
            request.getFull_name(),
            request.getClass_id(),
            parentId
        );
        
        if (updatedRows == 0) {
            throw new BusinessException(500, "Failed to update student");
        }
        
        // Fetch and return updated student
        Student updatedStudent = studentDao.findById(id)
                .orElseThrow(() -> new BusinessException(404, "Student not found"));
        
        StudentDto dto = new StudentDto();
        dto.setId(updatedStudent.getId());
        dto.setFull_name(updatedStudent.getFull_name());
        dto.setClass_id(updatedStudent.getClass_id());
        
        // Get parent phone if parent exists
        if (updatedStudent.getStudent_parent() != null) {
            UserDao.findById(updatedStudent.getStudent_parent())
                    .ifPresent(user -> dto.setParentPhone(user.getPhone()));
        }
        
        return dto;
    }
}
