<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Welcome to OA system</title>
</head>
<body>
<!-- When front-end send Http request, the path of request must start with "/" symbol and follow closely project name -->
<a href="<%=request.getContextPath() %>/list.jsp">Show department list</a>

</body>
</html>