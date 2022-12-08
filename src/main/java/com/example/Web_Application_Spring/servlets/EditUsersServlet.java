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

@WebServlet("/Edit-user")
public class EditUsersServlet extends HttpServlet {
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
                User user = userService.findUserByLogin(req.getParameter("person"));
                List<Role> roles = userService.findUserRole(req.getParameter("person"));

                req.setAttribute("loginUser", user.getLogin());
                req.setAttribute("passwordUser", user.getPassword());
                req.setAttribute("nameUser", user.getName());
                req.setAttribute("birthdayUser", user.getBirthday().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
                req.setAttribute("salaryUser", user.getSalary());
                for (Role role : roles) {
                    switch (role.getName()) {
                        case "USER" -> req.setAttribute("checkedUser", "checked");
                        case "ADMIN" -> req.setAttribute("checkedAdmin", "checked");
                        case "VIEWER" -> req.setAttribute("checkedViewer", "checked");
                        case "EDITOR" -> req.setAttribute("checkedEditor", "checked");
                        case "CONTRIBUTOR" -> req.setAttribute("checkedContributor", "checked");
                    }
                }

                req.setAttribute("title", "Edit");
                req.setAttribute("typePassword", "text");

                req.getRequestDispatcher("/WEB-INF/jsp/add-edit.jsp").forward(req, resp);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        boolean correct = false;
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        String birthday = req.getParameter("birthday");
        String salary = req.getParameter("salary");

        if (login.equals("") && password.equals("") && name.equals("") && birthday.equals("") && salary.equals("")) {
            resp.sendRedirect(req.getContextPath() + "/Edit-user?person=" + req.getParameter("person") + "&er=t");
        } else {
            if (login.equals("") || !Pattern.matches("[a-zA-Za-яА-Я0-9]+", login)) {
                resp.sendRedirect(req.getContextPath() + "/Edit-user?person=" + req.getParameter("person") + "&er=login");
            } else {
                if (password.equals("") || !Pattern.matches(".{8,}", password)) {
                    resp.sendRedirect(req.getContextPath() + "/Edit-user?person=" + req.getParameter("person") + "&er=password");
                } else {
                    if (name.equals("") || !Pattern.matches("[a-zA-Za-яА-Я]+", name)) {
                        resp.sendRedirect(req.getContextPath() + "/Edit-user?person=" + req.getParameter("person") + "&er=name");
                    } else {
                        if (birthday.equals("") || !Pattern.matches("[0-9]{2}.[0-9]{2}.[0-9]{4}", birthday)) {
                            resp.sendRedirect(req.getContextPath() + "/Edit-user?person=" + req.getParameter("person") + "&er=birthday");
                        } else {
                            if (salary.equals("") || !Pattern.matches("[0-9]+", salary)) {
                                resp.sendRedirect(req.getContextPath() + "/Edit-user?person=" + req.getParameter("person") + "&er=salary");
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
                                    resp.sendRedirect(req.getContextPath() + "/Edit-user?person=" + req.getParameter("person") + "&er=birthday");
                                } else {
                                    correct = userService
                                            .updateUser(new User((new Random()).nextLong(), login, password, name, birthday_, age, Integer.parseInt(salary), roles), req.getParameter("person"));

                                    if (correct) {
                                        resp.sendRedirect(req.getContextPath() + "/admin-users");
                                    } else {
                                        resp.sendRedirect(req.getContextPath() + "/Edit-user?person=" + req.getParameter("person") + "&er=login");

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
