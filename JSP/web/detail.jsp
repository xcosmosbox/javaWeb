<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang='en'>
<head>
    <meta charset='UTF-8'>
    <title>Detail</title>
</head>
<body>
    <h1>${username}dept detail</h1>
    <hr/>
    dept_no: ${dept.deptno}<br>
    dept_name: ${dept.dname} <br>
    dept_location: ${dept.loc} <br>

    <input type='button' value='return' onclick='window.history.back()'/>

</body>
</html>