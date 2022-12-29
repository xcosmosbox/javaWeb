package com.javaweb.adapter;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class LoginServlet extends GenericServlet{

    @Override
    public void init() {
        System.out.println("Login Servlet's init function running!");
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("Login successful!");
        PrintWriter out = servletResponse.getWriter();
        out.print("Login successful!");
        ServletConfig config = this.getServletConfig();
    }
}
