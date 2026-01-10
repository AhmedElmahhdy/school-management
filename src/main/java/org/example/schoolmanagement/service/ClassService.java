package org.example.schoolmanagement.service;

import org.example.schoolmanagement.dao.ClassDao;
import org.example.schoolmanagement.dto.ClassDto;
import org.example.schoolmanagement.mapper.ClassMapper;
import org.example.schoolmanagement.model.Classes;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Service
public class ClassService {
    private final ClassDao classDao;

    public ClassDto addClass(ClassDto request) {
         if (request.getTeacher_id() == null) {
            throw new IllegalArgumentException("Teacher ID is required");
        }
       Classes entity=ClassMapper.toEntity(request); 
      try {
            int id = classDao.insert(entity.getTeacher_id(),entity.getName()); 
            entity.setId(id);

        } catch (Exception e) {
            throw new RuntimeException("Failed to add class: " + e.getMessage());
        }
        
       return request; 
    }
}
