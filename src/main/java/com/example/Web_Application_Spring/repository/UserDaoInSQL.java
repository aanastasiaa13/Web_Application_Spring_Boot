package com.example.Web_Application_Spring.repository;

import com.example.Web_Application_Spring.mapper.UserMapper;
import com.example.Web_Application_Spring.model.Role;
import com.example.Web_Application_Spring.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Repository
public class UserDaoInSQL implements UserDao {
    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean create(User user) {
        boolean created = false;

        if (!userMapper.existsUserByLogin(user.getLogin())) {
            created = userMapper.createUser(user);
            if (user.getRole() != null) {
                for (Role role : user.getRole()) {
                    userMapper.insertUserRole(user.getLogin(), role.getRole_name());
                }
            }
        }

        return created;
    }

    @Override
    public boolean update(User user, String person) {
        boolean edited = false;

        try {
            if ((userMapper.existsUserByLogin(user.getLogin()) && user.getLogin().equals(person)) ||
                    (!userMapper.existsUserByLogin(user.getLogin()) && !user.getLogin().equals(person))) {
                edited = userMapper.
                        updateUser(
                                user.getLogin(), user.getPassword(),
                                user.getName(), new SimpleDateFormat("dd.MM.yyyy").parse(user.getBirthday().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))),
                                user.getAge(), user.getSalary(), person
                        );

                userMapper.deleteUserRole(user.getLogin());

                if (user.getRole() != null) {
                    for (Role role : user.getRole()) {
                        userMapper.insertUserRole(user.getLogin(), role.getRole_name());
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return edited;
    }

    @Override
    public void delete(String login) {
        userMapper.deleteUser(login);
    }

    public boolean existsByLoginAndPassword(String login, String password) {
        return userMapper.existsUserByLoginAndPassword(login, password);
    }

    @Override
    public Set<User> findAll() {
        Set<User> usersSet = userMapper.findAllUsers();
        for (User user : usersSet) {
            user.setRole(findRole(user.getLogin()));
        }

        return usersSet;
    }

    @Override
    public User findByLogin(String login) {
        return userMapper.findUserByLogin(login);
    }

    @Override
    public List<Role> findRole(String login) {
        return userMapper.findUserRole(login);
    }
}
