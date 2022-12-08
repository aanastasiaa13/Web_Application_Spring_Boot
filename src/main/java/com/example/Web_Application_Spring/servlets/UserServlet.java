package com.example.Web_Application_Spring.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/user")
public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("login") == null &&
            req.getSession().getAttribute("password") == null &&
            req.getSession().getAttribute("role") == null) {
            req.setAttribute("hide", "hidden");
            req.getRequestDispatcher("/WEB-INF/jsp/authorization.jsp").forward(req, resp);
        }
        else {
            if (req.getSession().getAttribute("role").toString().equals("ADMIN")) {
                resp.sendRedirect(req.getContextPath() + "/admin");
            } else {
                req.setAttribute("hideUser", "hidden");

                req.getRequestDispatcher("/WEB-INF/jsp/user.jsp").forward(req, resp);
            }
        }
    }
}