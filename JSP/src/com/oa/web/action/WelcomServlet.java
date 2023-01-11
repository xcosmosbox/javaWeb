package com.oa.web.action;

import com.oa.bean.DeptWarpper;
import com.oa.utils.DBUtil;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(name = "WelcomServlet", value = "/welcom")
public class WelcomServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        String username = null;
        String password = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                String name = cookie.getName();
                if("username".equals(name)){
                    username = cookie.getValue();
                } else if ("password".equals(name)) {
                    password =  cookie.getValue();
                }

            }

        }

        if (username != null && password != null){
            // Connect database and query all department
            Connection connection = null;
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;

            try {
                connection = DBUtil.getConnection();
                String sql = "select * from t_user where username = ? and password = ?";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1,username);
                preparedStatement.setString(2,password);

                resultSet = preparedStatement.executeQuery();


                if (resultSet.next()){
                    HttpSession httpSession = request.getSession();
                    httpSession.setAttribute("username",username);
                    response.sendRedirect(request.getContextPath() + "/dept/list");
                } else {
                    response.sendRedirect(request.getContextPath() + "/index.jsp");
                }



            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                DBUtil.close(connection,preparedStatement,resultSet);
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        }





    }
}
