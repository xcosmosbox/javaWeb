package com.javaweb.adapter;

import jakarta.servlet.*;

import java.io.IOException;

public abstract class GenericServlet implements Servlet {

    private ServletConfig config;

    @Override
    public final void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("My servlet configuration: " + servletConfig);
        this.config = servletConfig;
        this.init();
    }

    public void init(){
        //other init contents for myself
    }

    @Override
    public ServletConfig getServletConfig() {
        return config;
    }

    @Override
    public abstract void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException;

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
