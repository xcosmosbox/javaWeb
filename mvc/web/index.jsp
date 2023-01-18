<%--
  Created by IntelliJ IDEA.
  User: fengyuxiang
  Date: 18/1/2023
  Time: 14:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/">
    <title>Bank Transfer</title>
  </head>
  <body>
  <form action="transfer" method="post">
    payer: <input type="text" name="fromActno"><br>
    payee: <input type="text" name="toActno"><br>
    amount: <input type="text" name="money"><br>
    <input type="submit" value="pay">
  </form>
  </body>
</html>
