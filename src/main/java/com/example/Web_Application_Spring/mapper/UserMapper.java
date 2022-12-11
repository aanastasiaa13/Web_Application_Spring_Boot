package com.example.Web_Application_Spring.mapper;

import com.example.Web_Application_Spring.model.Role;
import com.example.Web_Application_Spring.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;

@Mapper
public interface UserMapper {
    boolean createUser(User user);

    boolean updateUser(@Param("login") String login, @Param("password") String password, @Param("name") String name, @Param("birthday") Date birthday, @Param("age") int age, @Param("salary") int salary, String person);

    void deleteUser(@Param("login") String login);

    void insertUserRole(@Param("login") String login, @Param("roleName") String roleName);

    void deleteUserRole(@Param("login") String login);

    boolean existsUserByLoginAndPassword(@Param("login") String login, @Param("password") String password);
    boolean existsUserByLogin(@Param("login") String login);

    LinkedHashSet<User> findAllUsers();
    User findUserByLogin(@Param("login") String login);
    List<Role> findUserRole(@Param("login") String login);
}
