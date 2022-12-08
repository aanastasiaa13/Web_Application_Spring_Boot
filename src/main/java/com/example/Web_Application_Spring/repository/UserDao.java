package com.example.Web_Application_Spring.repository;

import com.example.Web_Application_Spring.model.Role;
import com.example.Web_Application_Spring.model.User;

import java.util.List;
import java.util.Set;

public interface UserDao {
    boolean create(User user);

    boolean update(User user, String person);

    void delete(String login);

    boolean checkLogin(String login, String password); //delete

    boolean checkData(String login, String password, String name, String birthday, String salary, int age); //delete

    Set<User> findAll();

    User findByLogin(String login);

    List<Role> findRole(String login);
    boolean existsByLoginAndPassword(String login, String password);
}
