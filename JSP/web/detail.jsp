<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang='en'>
<head>
    <meta charset='UTF-8'>
    <title>Detail</title>
</head>
<body>
    <h1>dept detail</h1>
    <%
        String  dept_no = (String) request.getAttribute("dept_no");
        String  dept_name = (String) request.getAttribute("dept_name");
        String  location = (String) request.getAttribute("location");
    %>
    <hr/>
    dept_no: <%=dept_no%> <br>
    dept_name: <%=dept_name%> <br>
    dept_location: <%=location%> <br>

    <input type='button' value='return' onclick='window.history.back()'/>

</body>
</html>