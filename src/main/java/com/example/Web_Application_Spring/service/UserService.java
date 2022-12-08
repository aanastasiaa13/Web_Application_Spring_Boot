package com.example.Web_Application_Spring.service;

import com.example.Web_Application_Spring.model.Role;
import com.example.Web_Application_Spring.model.User;

import java.util.List;
import java.util.Set;

public interface UserService {

    boolean createUser(User user);

    boolean updateUser(User user, String person);

    void deleteUser(String login);

    boolean checkLogin(String login, String password); //delete

    boolean checkData(String login, String password, String name, String birthday, String salary, int age); //delete

    Set<User> findAllUsers();

    User findUserByLogin(String login);

    List<Role> findUserRole(String login);
    boolean existsUserByLoginAndPassword(String login, String password);
}
