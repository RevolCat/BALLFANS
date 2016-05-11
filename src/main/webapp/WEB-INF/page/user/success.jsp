<%--
  Created by IntelliJ IDEA.
  User: Revol
  Date: 2015/12/18
  Time: 14:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<a href="/">注册成功，5秒后返回首页。。。。。</a>
</body>
</html>
<script src="../media/JS/jquery.min.js"></script>
<script>
  $(document).ready(function(){
    setTimeout(function(){
      window.location.href="/";
    },5000);
  });
</script>