<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Welcome to OA system</title>
</head>
<body>
<!-- When front-end send Http request, the path of request must start with "/" symbol and follow closely project name -->

<%--<a href="<%=request.getContextPath() %>/dept/list">Show department list</a>--%>

    <h1>Login Page</h1>
    <hr>
    <form action="<%=request.getContextPath()%>/user/login" method="post">
        username: <input type="text" name="username"><br>
        password: <input type="password" name="password"><br>
        <input type="submit" value="login">
    </form>

</body>
</html>