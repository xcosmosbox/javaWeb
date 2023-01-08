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
<form action='<%=request.getContextPath() %>/list.jsp' method='get'>
  dept_no<input type='text' name='deptno' value='10' readonly/><br>
  dept_name<input type='text' name='dname' value='sale'/><br>
  dept_location<input type='text' name='loc' value='mel'/><br>
  <input type='submit' value='Update'/><br>
</form>
</body>
</html>