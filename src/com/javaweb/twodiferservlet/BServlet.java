package com.javaweb.twodiferservlet;

import jakarta.servlet.*;

import java.io.IOException;

public class BServlet implements Servlet {

    //no-params constructor
    public BServlet(){
        System.out.println("BServlet no-params constructor running!");
    }

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("BServlet init function running!");
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("BServlet service function running!");
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {
        System.out.println("BServlet destroy function running!");
    }
}
