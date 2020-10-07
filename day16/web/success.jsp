<%@ page import="live.nobug.domain.User" %><%--
  Created by IntelliJ IDEA.
  User: tianmengmeng
  Date: 2020/10/7
  Time: 16:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登陆成功</title>
</head>
<body>
    <%
        User user = (User) session.getAttribute("user");
    %>
    <h1>登陆成功！<%= user.getUsername()%>，欢迎您！</h1>
</body>
</html>
