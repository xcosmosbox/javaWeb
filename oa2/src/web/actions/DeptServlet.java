package web.actions;

import com.oa.utils.DBUtil;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(name = "DeptServlet", value = {"/dept/list", "/dept/save","/dept/edit","/dept/detail","/dept/delete","/dept/modify",})
//@WebServlet("/dept/*")
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

    private void doModify(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");

        String deptno = request.getParameter("deptno");
        String dname = request.getParameter("dname");
        String loc = request.getParameter("loc");

        System.out.println(deptno);
        System.out.println(dname);
        System.out.println(loc);

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
            response.sendRedirect(request.getContextPath() + "/error.html");

        }
    }

    private void doDel(HttpServletRequest request, HttpServletResponse response) throws IOException{
// Delete department according to the deptno
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

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
            response.sendRedirect(request.getContextPath() + "/error.html");

        }
    }

    private void doDetail(HttpServletRequest request, HttpServletResponse response) throws IOException{
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String deptno = request.getParameter("deptno");

        //Static block
        out.print("<!DOCTYPE html>");
        out.print("<html lang='en'>");
        out.print("<head>");
        out.print("    <meta charset='UTF-8'>");
        out.print("    <title>Detail</title>");
        out.print("</head>");
        out.print("<body>");
        out.print("   <h1>dept detail</h1>");
        out.print("   <hr/>");


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
                out.print("       dept_no: "+deptno+" <br>");
                out.print("       dept_name: "+dname+" <br>");
                out.print("       dept_location: "+loc+" <br>");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,preparedStatement,resultSet);
        }

        //Static block
        out.print("");
        out.print("   <input type='button' value='return' onclick='window.history.back()'/>");
        out.print("");
        out.print("</body>");
        out.print("</html>");

    }

    private void doEdit(HttpServletRequest request, HttpServletResponse response) throws IOException{
//Getting root path for application
        String contextPath = request.getContextPath();

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String deptno = request.getParameter("deptno");

        out.print("<!DOCTYPE html>");
        out.print("<html lang='en'>");
        out.print("<head>");
        out.print("  <meta charset='UTF-8'>");
        out.print("  <title>Edit dept</title>");
        out.print("</head>");
        out.print("<body>");
        out.print("<h1>Edit dept</h1>");
        out.print("<hr/>");
        out.print("<form action='"+contextPath+"/dept/modify' method='post'>");




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

                out.print("               dept_no<input type='text' name='deptno' value='"+deptno+"' readonly/><br>");
                out.print("               dept_name<input type='text' name='dname' value='"+dname+"'/><br>");
                out.print("               dept_location<input type='text' name='loc' value='"+location+"'/><br>");

            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,preparedStatement,resultSet);
        }

        out.print(" <input type='submit' value='Update'/><br>");
        out.print("</form>");
        out.print("</body>");
        out.print("</html>");
    }

    private void doSave(HttpServletRequest request, HttpServletResponse response) throws IOException{
//        response.setContentType("text/html");
//        PrintWriter out = response.getWriter();

        request.setCharacterEncoding("UTF-8");

        String deptno = request.getParameter("deptno");
        String dname = request.getParameter("dname");
        String loc = request.getParameter("loc");

        System.out.println(deptno);
        System.out.println(dname);
        System.out.println(loc);

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
            response.sendRedirect(request.getContextPath() + "/error.html");

        }
    }

    private void doList(HttpServletRequest request, HttpServletResponse response) throws IOException{
//Getting root path for application
        String contextPath = request.getContextPath();



        // Set type of response context and type of character set to avoid error encoding
//        resp.setContentType("text/html;charset=UTF-8");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        //Static output statements
        out.print("<!DOCTYPE html>");
        out.print("<html lang='en'>");
        out.print("<head>");
        out.print("    <meta charset='UTF-8'>");
        out.print("    <title>dept list</title>");

        out.print("<script type='text/javascript'>");
        out.print("function del(del_no) {");
        out.print("    if(window.confirm('Double check for deleting!')){");
        out.print("        document.location.href = '"+contextPath+"/dept/delete?deptno=' + del_no;");
        out.print("    }");
        out.print("}");
        out.print("</script>");

        out.print("</head>");
        out.print("<body>");




        out.print("  <h1 align='center'>dept list</h1>");
        out.print("  <hr />");
        out.print("  <table border='1px' align='center' width='50%'>");
        out.print("    <tr>");
        out.print("      <th>serial_no</th>");
        out.print("      <th>dept_no</th>");
        out.print("      <th>dept_name</th>");
        out.print("      <th>operation</th>");
        out.print("    </tr>");






        // Connect database and query all department
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DBUtil.getConnection();
            String sql = "select deptno, dname, loc from dept";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            // Process the result set
            int i = 0;
            while (resultSet.next()){
                String deptno = resultSet.getString("deptno");
                String dname = resultSet.getString("dname");
                String loc = resultSet.getString("loc");

                out.print("    <tr>");
                out.print("      <td>"+(++i)+"</td>");
                out.print("      <td>"+deptno+"</td>");
                out.print("      <td>"+dname+"</td>");
                out.print("      <td>");
                out.print("        <a href='javascript:void(0)' onclick='del("+deptno+")'>delete</a>");
                out.print("        <a href='"+contextPath+"/dept/edit?deptno="+deptno+"'>modify</a>");
                out.print("        <a href='"+contextPath+"/dept/detail?deptno="+deptno+"'>more info</a>");
                out.print("      </td>");
                out.print("    </tr>");

            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,preparedStatement,resultSet);
        }

        //Static output statements
        out.print("  </table>");
        out.print("  <hr />");
        out.print("  <a href='"+contextPath+"/add.html'>add new dept</a>");
        out.print("</body>");
        out.print("</html>");
    }
}
