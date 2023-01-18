package com.javaweb.servlet;

import jakarta.servlet.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

//@WebFilter(filterName = "MyFilter", value = {"/a.do","/b.do"})
@WebFilter(filterName = "MyFilter", value = {"*.do"})
public class MyFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
        System.out.println("do filter init function");
    }

    public void destroy() {
        System.out.println("do filter destroy function");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        System.out.println("do filter doFilter function");
        chain.doFilter(request, response);
    }
}
