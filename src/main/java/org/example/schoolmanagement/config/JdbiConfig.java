package org.example.schoolmanagement.config;

import org.example.schoolmanagement.dao.ClassDao;
import org.example.schoolmanagement.dao.EmployeeDao;
import org.example.schoolmanagement.dao.StudentDao;
import org.example.schoolmanagement.dao.UserDao;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class JdbiConfig {

    @Bean
    public Jdbi jdbi(DataSource dataSource) {
        Jdbi jdbi = Jdbi.create(dataSource);
        jdbi.installPlugin(new SqlObjectPlugin());
        return jdbi;
    }

    @Bean
    public UserDao userDao(Jdbi jdbi) {
        return jdbi.onDemand(UserDao.class);
    }

     @Bean
    public EmployeeDao employeeDao(Jdbi jdbi) {
        return jdbi.onDemand(EmployeeDao.class);
    }

    @Bean
    public ClassDao classDao(Jdbi jdbi){
        return jdbi.onDemand(ClassDao.class);
    }
    
    @Bean
    public StudentDao studentDao(Jdbi jdbi){

        return jdbi.onDemand(StudentDao.class);
    }
}
