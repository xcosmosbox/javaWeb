package com.javaWeb.servlet;

import jakarta.servlet.annotation.WebServlet;

public class ReflectAnnotation {
    public static void main(String[] args) throws Exception {
        Class<?> helloServletClass = Class.forName("com.javaWeb.servlet.HelloServlet");

        // Check it whether have annotation for WebServlet
        boolean helloServletClassAnnotation = helloServletClass.isAnnotationPresent(WebServlet.class);

        System.out.println(helloServletClassAnnotation);

        // if yes, getting WebServlet annotation
        if (helloServletClassAnnotation){
            WebServlet webServletAnnotation = helloServletClass.getAnnotation(WebServlet.class);

            // getting WebServlet annotation
            String[] values = webServletAnnotation.value();
            for (String value :
                    values) {
                System.out.println(value);
            }
        }

    }
}
