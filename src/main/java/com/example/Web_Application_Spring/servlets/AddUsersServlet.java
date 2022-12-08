package com.example.Web_Application_Spring.servlets;

import com.example.Web_Application_Spring.model.Role;
import com.example.Web_Application_Spring.model.User;
import com.example.Web_Application_Spring.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

@WebServlet("/Add-user")
public class AddUsersServlet extends HttpServlet {
    @Autowired
    private UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("login") == null &&
                req.getSession().getAttribute("password") == null &&
                req.getSession().getAttribute("role") == null) {
            req.setAttribute("hide", "hidden");
            req.getRequestDispatcher("/WEB-INF/jsp/authorization.jsp").forward(req, resp);
        } else {
            if (req.getSession().getAttribute("role").toString().equals("USER")) {
                resp.sendRedirect(req.getContextPath() + "/user");
            } else {
                req.setAttribute("title", "Add");
                req.setAttribute("typePassword", "password");

                req.getRequestDispatcher("/WEB-INF/jsp/add-edit.jsp").forward(req, resp);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        boolean added = false;

        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        String birthday = req.getParameter("birthday");
        String salary = req.getParameter("salary");

        if (login.equals("") && password.equals("") && name.equals("") && birthday.equals("") && salary.equals("")) {
            resp.sendRedirect(req.getContextPath() + "/Add-user?er=t");
        } else {
            if (login.equals("") || !Pattern.matches("[a-zA-Za-яА-Я0-9]+", login)) {
                resp.sendRedirect(req.getContextPath() + "/Add-user?er=login");
            } else {
                if (password.equals("") || !Pattern.matches(".{8,}", password)) {
                    resp.sendRedirect(req.getContextPath() + "/Add-user?er=password");
                } else {
                    if (name.equals("") || !Pattern.matches("[a-zA-Za-яА-Я]+", name)) {
                        resp.sendRedirect(req.getContextPath() + "/Add-user?er=name");
                    } else {
                        if (birthday.equals("") || !Pattern.matches("[0-9]{2}.[0-9]{2}.[0-9]{4}", birthday)) {
                            resp.sendRedirect(req.getContextPath() + "/Add-user?er=birthday");
                        } else {
                            if (salary.equals("") || !Pattern.matches("[0-9]+", salary)) {
                                resp.sendRedirect(req.getContextPath() + "/Add-user?er=salary");
                            } else {
                                LocalDate birthday_ = LocalDate.parse(req.getParameter("birthday"), DateTimeFormatter.ofPattern("dd.MM.yyyy"));
                                int age = Period.between(birthday_, LocalDate.now()).getYears();
                                List<Role> roles = new ArrayList<Role>();
                                if (req.getParameterValues("role") != null) {
                                    for (String element : Arrays.asList(req.getParameterValues("role"))) {
                                        roles.add(new Role((new Random()).nextLong(), element));
                                    }
                                }

                                if (age < 18) {
                                    resp.sendRedirect(req.getContextPath() + "/Add-user?er=birthday");
                                } else {
                                    added = userService
                                            .createUser(new User((new Random()).nextLong(), login, password, name, birthday_, age, Integer.parseInt(salary), roles));

                                    if (added) {
                                        resp.sendRedirect(req.getContextPath() + "/admin-users");
                                    } else {
                                        resp.sendRedirect(req.getContextPath() + "/Add-user?er=t");

                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
