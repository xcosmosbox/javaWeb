package com.javaWeb.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "HelloServlet", value = "/HelloServlet", urlPatterns = {"/hello", "hello2"}, loadOnStartup = 1,
initParams = {@WebInitParam(name = "username", value = "root"), @WebInitParam(name = "password", value = "123")})
public class HelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Getting root path for application
        String contextPath = request.getContextPath();

        // Set type of response context and type of character set to avoid error encoding
//        resp.setContentType("text/html;charset=UTF-8");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
