package com.example.Web_Application_Spring.servlets;

import com.example.Web_Application_Spring.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

@WebServlet("/admin-users")
public class AdminUsersServlet extends HttpServlet {
    @Autowired
    private UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("role").toString().equals("USER")) {
            resp.sendRedirect(req.getContextPath() + "/user");
        } else {
            req.setAttribute("users", userService.findAllUsers());

            req.getRequestDispatcher("/WEB-INF/jsp/admin-users.jsp").forward(req, resp);
        }
    }
}
