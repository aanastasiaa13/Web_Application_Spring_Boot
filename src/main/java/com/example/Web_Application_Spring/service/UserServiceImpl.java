package com.example.Web_Application_Spring.service;

import com.example.Web_Application_Spring.model.Role;
import com.example.Web_Application_Spring.model.User;
import com.example.Web_Application_Spring.repository.UserDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    @Override
    public boolean createUser(User user) {
        return userDao.create(user);
    }

    @Override
    public boolean updateUser(User user, String person) {
        return userDao.update(user, person);
    }

    @Override
    public void deleteUser(String login) {
        userDao.delete(login);
    }

    @Override
    public Set<User> findAllUsers() {
        return userDao.findAll();
    }

    @Override
    public User findUserByLogin(String login) {
        return userDao.findByLogin(login);
    }

    @Override
    public List<Role> findUserRole(String login) {
        return userDao.findRole(login);
    }

    @Override
    public boolean existsUserByLoginAndPassword(String login, String password) {
        return userDao.existsByLoginAndPassword(login, password);
    }
}
