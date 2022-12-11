package com.example.Web_Application_Spring.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter("/*")
public class PageFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        String uriPath = request.getRequestURI().substring(request.getContextPath().length());

        if (uriPath.endsWith(".css") || uriPath.endsWith(".jpg") || uriPath.endsWith(".png") || uriPath.endsWith(".js")) {
            filterChain.doFilter(servletRequest, servletResponse);
        }

        if (session.getAttribute("login") == null && session.getAttribute("password") == null && session.getAttribute("role") == null) {
            if (uriPath.equals("/autho")) {
                filterChain.doFilter(servletRequest, servletResponse);
            }
            else {
                request.getServletContext().getRequestDispatcher(request.getContextPath() + "/autho").forward(request, response);
            }
        } else {
            switch (uriPath) {
                case "/user" ->
                        request.getServletContext().getRequestDispatcher(request.getContextPath() + "/user").forward(request, response);
                case "/admin" ->
                        request.getServletContext().getRequestDispatcher(request.getContextPath() + "/admin").forward(request, response);
                case "/admin-users" ->
                        request.getServletContext().getRequestDispatcher(request.getContextPath() + "/admin-users").forward(request, response);
                case "/Add-user" ->
                        request.getServletContext().getRequestDispatcher(request.getContextPath() + "/Add-user").forward(request, response);
                case "/Edit-user" ->
                        request.getServletContext().getRequestDispatcher(request.getContextPath() + "/Edit-user").forward(request, response);
                case "/delete-user" ->
                        request.getServletContext().getRequestDispatcher(request.getContextPath() + "/delete-user").forward(request, response);
                case "/logout" ->
                        request.getServletContext().getRequestDispatcher(request.getContextPath() + "/logout").forward(request, response);
            }
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
