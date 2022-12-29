package com.javaweb.twodiferservlet;

import jakarta.servlet.*;

import java.io.IOException;

public class AServlet implements Servlet {

    //no-params constructor
    public AServlet()
    {
        System.out.println("AServlet no-params constructor running!");
    }

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("AServlet init function running!");
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("AServlet service function running!");
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {
        System.out.println("AServlet destroy function running!");
    }
}
