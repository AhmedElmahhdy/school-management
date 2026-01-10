package org.example.schoolmanagement.dao;

import java.util.Optional;
import org.example.schoolmanagement.model.Classes;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;


@RegisterBeanMapper(Classes.class)
public interface ClassDao {
   
      @SqlUpdate("INSERT INTO classes(teacher_id, name) VALUES (:teacher_id, :name)")
      int insert(@Bind("teacher_id") Integer teacher_id,
           @Bind("name") String name);
      

      @SqlQuery("select * from classes where id =:id")
      Optional<Classes> findById(@Bind("id") Integer id);     
}