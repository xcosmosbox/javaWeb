package com.oa.web.action;

import com.oa.bean.AdminUserBean;
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

@WebServlet(name = "UserServlet", value = {"/user/login","/user/exit"})
public class UserServlet extends HttpServlet {
    /**
     * Receives standard HTTP requests from the public
     * <code>service</code> method and dispatches
     * them to the <code>do</code><i>Method</i> methods defined in
     * this class. This method is an HTTP-specific version of the
     * {@link Servlet#service} method. There's no
     * need to override this method.
     *
     * @param request  the {@link HttpServletRequest} object that
     *             contains the request the client made of
     *             the servlet
     * @param response the {@link HttpServletResponse} object that
     *             contains the response the servlet returns
     *             to the client
     * @throws IOException      if an input or output error occurs
     *                          while the servlet is handling the
     *                          HTTP request
     * @throws ServletException if the HTTP request
     *                          cannot be handled
     * @see Servlet#service
     */
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                String name = cookie.getName();
                String value = cookie.getValue();
            }

        }

        String servletPath = request.getServletPath();
        if ("/user/login".equals(servletPath)){
            doLogin(request,response);
        }
        else if("/user/exit".equals(servletPath)){
            doExit(request,response);
        }
    }


    protected void doExit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //destroy the session
        HttpSession httpSession = request.getSession(false);
        if (httpSession != null) {
            // manual destroy the session
            httpSession.invalidate();

            // redirect to login page
            response.sendRedirect(request.getContextPath());
        }

    }


    protected void doLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        boolean success = false;

        // JDBC
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
                success = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,preparedStatement,resultSet);
        }

        if (success){
            HttpSession httpSession = request.getSession();
            httpSession.setAttribute("username",username);

            if ("1".equals(request.getParameter("auto_login"))){
                Cookie cookie = new Cookie("username",username);
                Cookie cookie_pwd = new Cookie("password",password);

                cookie.setMaxAge(60 * 60 * 24 * 10);
                cookie_pwd.setMaxAge(60 * 60 * 24 * 10);

                cookie.setPath(request.getContextPath());
                cookie_pwd.setPath(request.getContextPath());

                response.addCookie(cookie);
                response.addCookie(cookie_pwd);

            }

            response.sendRedirect(request.getContextPath()+"/dept/list");
        }else {
            response.sendRedirect(request.getContextPath()+"/login_error.jsp");
        }


    }
}
