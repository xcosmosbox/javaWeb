package com.oa.web.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

//@WebFilter(filterName = "LoginFilter")
public class LoginFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        HttpSession httpSession = request.getSession(false);
        if (httpSession != null && httpSession.getAttribute("username") != null) {
            chain.doFilter(request, response);
        }
        else {
//            response.sendRedirect(request.getContextPath()+"/index.jsp");
            // go to welcom page
            response.sendRedirect( request.getContextPath()+"/welcom");
        }

    }
}
