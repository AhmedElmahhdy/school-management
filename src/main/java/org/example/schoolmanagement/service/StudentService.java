package org.example.schoolmanagement.service;

import org.example.schoolmanagement.dao.ClassDao;
import org.example.schoolmanagement.dao.UserDao;
import org.example.schoolmanagement.dao.StudentDao;
import org.example.schoolmanagement.dto.StudentDto;
import org.example.schoolmanagement.model.Student;
import org.springframework.stereotype.Service;
import org.example.schoolmanagement.utils.BusinessException;

import lombok.RequiredArgsConstructor;

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
}
