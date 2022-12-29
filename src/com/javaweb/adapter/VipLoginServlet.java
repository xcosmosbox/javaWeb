package com.javaweb.adapter;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class VipLoginServlet extends GenericServlet{
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        PrintWriter out = servletResponse.getWriter();
        out.print("Vip login successful!");
    }
}
