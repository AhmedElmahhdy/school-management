package org.example.schoolmanagement.mapper;

import org.example.schoolmanagement.dto.ClassDto;
import org.example.schoolmanagement.model.Classes;


public class ClassMapper {

    public static Classes toEntity(ClassDto dto) {
        Classes entity = new Classes();
        entity.setTeacher_id(dto.getTeacher_id());
        entity.setName(dto.getName());
        return entity;
    }

    public static ClassDto toDto(Classes entity) {
        ClassDto dto = new ClassDto();
        dto.setTeacher_id(entity.getTeacher_id());
        dto.setName(entity.getName());
        return dto;
    }
}