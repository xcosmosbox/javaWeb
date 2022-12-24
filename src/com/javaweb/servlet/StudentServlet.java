package com.javaweb.servlet;

import jakarta.servlet.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ResourceBundle;

public class StudentServlet implements Servlet {
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        // set type of response
        servletResponse.setContentType("text/html");
        PrintWriter out = servletResponse.getWriter();

        // resources binding
        ResourceBundle bundle = ResourceBundle.getBundle("resources/db");

        // Get info from bundle
        String driver = bundle.getString("driver");
        String url = bundle.getString("url");
        String user = bundle.getString("user");
        String password = bundle.getString("password");


        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try{
            // 1. Sign up the driver for database
            Class.forName(driver);

            // 2. Pull connection
            connection = DriverManager.getConnection(url, user, password);

            // 3. Manipulate data object
            statement = connection.createStatement();

            // 4. Launch the sql statement
            String sql_query = "select * from TbStudent";
            resultSet = statement.executeQuery(sql_query);

            // 5. Operate on the result set
            while (resultSet.next()){
                String stuid = resultSet.getString("stuid");
                String stuname = resultSet.getString("stuname");
                String stusex = resultSet.getString("stusex");
                java.sql.Date stubirth = resultSet.getDate("stubirth");
                String stuaddr = resultSet.getString("stuaddr");
                out.print(stuid + " | " + stuname + " | " + stusex + " | " + stubirth.toString() + " | " + stuaddr + "<br>");
//                System.out.println(stuid + " | " + stuname + " | " + stusex + " | " + stubirth.toString() + " | " + stuaddr);
            }


        } catch (SQLException e){
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            if(resultSet != null){
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (statement != null){
                try {
                    statement.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }







    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
