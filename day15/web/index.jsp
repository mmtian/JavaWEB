<%--
  Created by IntelliJ IDEA.
  User: tianmengmeng
  Date: 2020/10/6
  Time: 9:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>首页</title>
</head>
<body>
<img id="img" src="/day15/checkCodeServlet">
<a id="change" href="void:javascript();">看不清？换一张</a>
</body>
<script>
    var img = document.getElementById("img");
    img.onclick = function () {
        var date = new Date().getTime();
        img.src = "/day15/checkCodeServlet?" + date;
    }
    document.getElementById("change").onclick = img.onclick;
</script>
</html>
