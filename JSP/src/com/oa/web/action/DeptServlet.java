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
import java.util.ArrayList;

@WebServlet(name = "DeptServlet", value = {"/dept/list", "/dept/save","/dept/edit","/dept/detail","/dept/delete","/dept/modify"})
public class DeptServlet extends HttpServlet {
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
        HttpSession httpSession = request.getSession(false);
        if (httpSession != null && httpSession.getAttribute("username") != null) {
            String servletPath = request.getServletPath();
            if ("/dept/list".equals(servletPath)){
                doList(request,response);
            } else if ("/dept/save".equals(servletPath)) {
                doSave(request,response);
            } else if ("/dept/edit".equals(servletPath)) {
                doEdit(request,response);
            } else if ("/dept/detail".equals(servletPath)) {
                doDetail(request,response);
            } else if ("/dept/delete".equals(servletPath)) {
                doDel(request,response);
            } else if ("/dept/modify".equals(servletPath)) {
                doModify(request,response);
            }
        }
        else {
//            response.sendRedirect(request.getContextPath()+"/index.jsp");
            // go to welcom page
            response.sendRedirect( request.getContextPath()+"/welcom");
        }


    }

    private void doList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        // Process the result set
        ArrayList deptArrayList = new ArrayList<DeptWarpper>();

        // Connect database and query all department
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DBUtil.getConnection();
            String sql = "select deptno, dname, loc from dept";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();


            while (resultSet.next()){
                String deptno = resultSet.getString("deptno");
                String dname = resultSet.getString("dname");
                String loc = resultSet.getString("loc");

                deptArrayList.add(new DeptWarpper(deptno,dname,loc));
            }



        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,preparedStatement,resultSet);
        }

        // request forward
        try {
            request.setAttribute("deptArrayList",deptArrayList);
            request.getRequestDispatcher("/list.jsp").forward(request,response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void doSave(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String deptno = request.getParameter("deptno");
        String dname = request.getParameter("dname");
        String loc = request.getParameter("loc");

        // JDBC
        // Connect database and query all department
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int count = 0;
        try {
            connection = DBUtil.getConnection();

            String sql = "insert into dept(deptno, dname, loc) values(?,?,?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,deptno);
            preparedStatement.setString(2,dname);
            preparedStatement.setString(3,loc);

            count = preparedStatement.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,preparedStatement,resultSet);
        }

        if (count == 1){
            // success
            // forward servlet
//            request.getRequestDispatcher("/dept/list").forward(request,response);

            // redirect
            response.sendRedirect(request.getContextPath() + "/dept/list");
        }
        else {
//            request.getRequestDispatcher("/error.html").forward(request,response);
            response.sendRedirect(request.getContextPath() + "/error.jsp");

        }
    }

    private void doEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String deptno = request.getParameter("deptno");
        // JDBC
        // Connect database and query all department
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DBUtil.getConnection();

            String sql = "select dname, loc as location from dept where deptno = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,deptno);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                String dname = resultSet.getString("dname");
                String location = resultSet.getString("location");

                request.setAttribute("wasEditEdpt",new DeptWarpper(deptno,dname,location));

//                out.print("               dept_no<input type='text' name='deptno' value='"+deptno+"' readonly/><br>");
//                out.print("               dept_name<input type='text' name='dname' value='"+dname+"'/><br>");
//                out.print("               dept_location<input type='text' name='loc' value='"+location+"'/><br>");

            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,preparedStatement,resultSet);
        }

        request.getRequestDispatcher("/edit.jsp").forward(request,response);

    }

    private void doDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String deptno = request.getParameter("deptno");

        // JDBC
        // Connect database and query all department
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DBUtil.getConnection();
            String sql = "select dname, loc from dept where deptno = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,deptno);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                String dname = resultSet.getString("dname");
                String loc = resultSet.getString("loc");
                request.setAttribute("dept",new DeptWarpper(deptno,dname,loc));
//                out.print("       dept_no: "+deptno+" <br>");
//                out.print("       dept_name: "+dname+" <br>");
//                out.print("       dept_location: "+loc+" <br>");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,preparedStatement,resultSet);
        }

        try {
            request.getRequestDispatcher("/detail.jsp").forward(request,response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void doDel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String deptno = request.getParameter("deptno");

        // JDBC
        // Connect database and query all department
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int count = 0;
        try {
            connection = DBUtil.getConnection();

            // Open workflow
            connection.setAutoCommit(false);

            String sql = "delete from dept where deptno = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,deptno);
            count = preparedStatement.executeUpdate();

            // commit
            connection.commit();

        } catch (SQLException e) {
            // Rollback when exception has been thrown
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,preparedStatement,resultSet);
        }

        if (count == 1){
            // success
            // forward servlet
//            request.getRequestDispatcher("/dept/list").forward(request,response);
            response.sendRedirect(request.getContextPath() + "/dept/list");

        }
        else {
//            request.getRequestDispatcher("/error.html").forward(request,response);
            response.sendRedirect(request.getContextPath() + "/error.jsp");

        }
    }

    private void doModify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String deptno = request.getParameter("deptno");
        String dname = request.getParameter("dname");
        String loc = request.getParameter("loc");
        // JDBC
        // Connect database and query all department
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int count = 0;
        try {
            connection = DBUtil.getConnection();

            String sql = "update dept set dname = ?, loc = ? where deptno = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,dname);
            preparedStatement.setString(2,loc);
            preparedStatement.setString(3,deptno);

            count = preparedStatement.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,preparedStatement,resultSet);
        }

        if (count == 1){
            // success
            // forward servlet
//            request.getRequestDispatcher("/dept/list").forward(request,response);

            // redirect
            response.sendRedirect(request.getContextPath() + "/dept/list");
        }
        else {
//            request.getRequestDispatcher("/error.html").forward(request,response);
            response.sendRedirect(request.getContextPath() + "/error.jsp");

        }
    }
}
