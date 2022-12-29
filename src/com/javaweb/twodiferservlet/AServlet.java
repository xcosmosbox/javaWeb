package com.javaweb.twodiferservlet;

import jakarta.servlet.*;

import java.io.IOException;

public class AServlet implements Servlet {

    //no-params constructor
    //only one times for running
    public AServlet()
    {
        System.out.println("AServlet no-params constructor running!");
    }

    //only one times for running
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("AServlet init function running!");
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    //The user calls several times and run several times
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("AServlet service function running!");
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    //only one times for release resources
    @Override
    public void destroy() {
        System.out.println("AServlet destroy function running!");
    }
}
