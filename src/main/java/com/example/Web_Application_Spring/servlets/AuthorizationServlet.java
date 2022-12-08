package com.example.Web_Application_Spring.servlets;

import com.example.Web_Application_Spring.model.Role;
import com.example.Web_Application_Spring.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.io.IOException;

@WebServlet("/autho")
public class AuthorizationServlet extends HttpServlet {
    @Autowired
    private UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("user") != null &&
                req.getSession().getAttribute("password") != null &&
                req.getSession().getAttribute("role") != null) {
            if (req.getSession().getAttribute("role").toString().equals("USER")) {
                resp.sendRedirect(req.getContextPath() + "/user");
            }

            if (req.getSession().getAttribute("role").toString().equals("ADMIN")) {
                resp.sendRedirect(req.getContextPath() + "/admin");
            }
        } else {
            req.setAttribute("hide", "hidden");

            req.getRequestDispatcher("/WEB-INF/jsp/authorization.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        String login = req.getParameter("login");
        String password = req.getParameter("password");

        if (login.equals("")) {
            resp.sendRedirect(req.getContextPath() + "/autho?er=login");
        } else {
            if (password.equals("")) {
                resp.sendRedirect(req.getContextPath() + "/autho?er=password");
            } else {
                if (userService.existsUserByLoginAndPassword(login, password)) {
                    session.setAttribute("user", login);
                    session.setAttribute("password", password);

                    for (Role role : userService.findUserRole(login)) {
                        if (role.getName().equals("ADMIN") || role.getName().equals("EDITOR")) {
                            resp.sendRedirect(req.getContextPath() + "/admin");
                            session.setAttribute("role", "ADMIN");
                        } else {
                            resp.sendRedirect(req.getContextPath() + "/user");
                            session.setAttribute("role", "USER");
                            break;
                        }
                    }
                } else {
                    resp.sendRedirect(req.getContextPath() + "/autho?er=login-and-password");
                }
            }
        }
    }
}
