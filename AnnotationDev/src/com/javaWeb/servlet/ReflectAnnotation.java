package com.javaWeb.servlet;

import jakarta.servlet.annotation.WebServlet;

public class ReflectAnnotation {
    public static void main(String[] args) throws Exception {
        Class<?> helloServletClass = Class.forName("com.javaWeb.servlet.HelloServlet");

        boolean helloServletClassAnnotation = helloServletClass.isAnnotationPresent(WebServlet.class);

        System.out.println(helloServletClassAnnotation);

        if (helloServletClassAnnotation){
            WebServlet webServletAnnotation = helloServletClass.getAnnotation(WebServlet.class);
            
        }

    }
}
