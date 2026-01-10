package org.example.schoolmanagement.dao;

import org.example.schoolmanagement.dto.UpdateEmployeeRequest;
import org.example.schoolmanagement.model.User;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;
import java.util.Optional;

@RegisterBeanMapper(User.class)
public interface UserDao {

    @SqlQuery("SELECT * FROM users WHERE id = :id")
    Optional<User> findById(@Bind("id") int id);

    // ==========================
    // Find user by username
    // ==========================
    @SqlQuery("SELECT * FROM users WHERE username = :username")
    Optional<User> findByUsername(@Bind("username") String username);

    @SqlQuery("SELECT * FROM users WHERE phone = :phone")
    Optional<User> findByPhoneNumber(@Bind("phone") String phone);

    // ==========================
    // Insert new user
    // ==========================
    @SqlUpdate("INSERT INTO users(username, password, role, address, phone, email, is_active) " +
               "VALUES(:username, :password, :role::user_role, :address, :phone, :email, :isActive)")
    @GetGeneratedKeys
    int save(@BindBean User user);

    // ==========================
    // Update existing user
    // ==========================
    @SqlUpdate("UPDATE users SET " +
               "username = COALESCE(:username, username), " +
               "email = COALESCE(:email, email), " +
               "phone = COALESCE(:phone, phone), " +
               "address = COALESCE(:address, address), " +
               "role = COALESCE(:role::user_role, role), " +
               "is_active = COALESCE(:isActive, is_active) " +
               "WHERE id = :id")
    int updateUser(@Bind("id") int id, @BindBean UpdateEmployeeRequest request);


    @SqlQuery("SELECT id FROM users WHERE phone = :phone")
    Integer findParentIdByPhone(@Bind("phone") String phone);
    
}
