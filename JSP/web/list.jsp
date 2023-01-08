<%@ page import="java.util.ArrayList" %>
<%@ page import="com.oa.bean.DeptWarpper" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang='en'>
<head>
    <meta charset='UTF-8'>
    <title>dept list</title>
</head>
<body>

  <script type='text/javascript'>
    function del(del_no) {
      var ok = window.confirm('Double check for deleting!')
      if(ok){
        document.location.href = '<%=request.getContextPath() %>/delete?deptno=' + del_no;
      }
    }

  </script>


  <h1 align='center'>dept list</h1>
  <hr />
  <table border='1px' align='center' width='50%'>
    <tr>
      <th>serial_no</th>
      <th>dept_no</th>
      <th>dept_name</th>
      <th>operation</th>
    </tr>

    <%
      ArrayList<DeptWarpper> arrayList = (ArrayList) request.getAttribute("deptArrayList");
    %>
    <%
      int i = 0;
      for (DeptWarpper dept: arrayList
            )
    { %>


      <tr>
        <td><%=(++i)%></td>
        <td><%=dept.getDeptno()%></td>
        <td><%=dept.getDname()%></td>
        <td>
          <a href='javascript:void(0)' onclick='del(10)'>delete</a>
          <a href='<%=request.getContextPath() %>/edit.jsp'>modify</a>
          <a href='<%=request.getContextPath() %>/detail.jsp'>more info</a>
        </td>
      </tr>
    <%} %>


  </table>
  <hr />
  <a href='<%=request.getContextPath() %>/add.jsp'>add new dept</a>
</body>
</html>