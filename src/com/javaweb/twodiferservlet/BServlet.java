package com.javaweb.twodiferservlet;

import jakarta.servlet.*;

import java.io.IOException;

public class BServlet implements Servlet {

    //no-params constructor
    //only one times for running
    public BServlet(){
        System.out.println("BServlet no-params constructor running!");
    }

    //only one times for running
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("BServlet init function running!");
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    //The user calls several times and run several times
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("BServlet service function running!");
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    //only one times for release resources
    @Override
    public void destroy() {
        System.out.println("BServlet destroy function running!");
    }
}
