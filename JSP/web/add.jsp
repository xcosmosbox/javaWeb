<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang='en'>
<head>
    <meta charset='UTF-8'>
    <title>Add a new dept</title>
</head>
<body>
  <h1>add new dept</h1>
  <hr/>
  <form action='/oa_module/dept/save' method='post'>
    dept_no<input type='text' name='deptno'/><br>
    dept_name<input type='text' name='dname'/><br>
    dept_location<input type='text' name='loc'/><br>
    <input type='submit' value='save'/><br>
  </form>

</body>
</html>