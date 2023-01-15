<%@ page import="com.oa.bean.DeptWarpper" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang='en'>
<head>
  <meta charset='UTF-8'>
  <title>Edit dept</title>
</head>
<body>
<h1>Edit dept</h1>
<hr/>
<%
  DeptWarpper deptWarpper = (DeptWarpper) request.getAttribute("wasEditEdpt");
%>
<form action='${pageContext.request.contextPath}/dept/modify' method='post'>
  dept_no<input type='text' name='deptno' value='${wasEditEdpt.deptno}' readonly/><br>
  dept_name<input type='text' name='dname' value='${wasEditEdpt.dname}'/><br>
  dept_location<input type='text' name='loc' value='${wasEditEdpt.loc}'/><br>
  <input type='submit' value='Update'/><br>
</form>
</body>
</html>