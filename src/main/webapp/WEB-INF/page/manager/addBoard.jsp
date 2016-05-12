<%--
  Created by IntelliJ IDEA.
  User: Revol
  Date: 2016/1/19
  Time: 11:05
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java"   isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title></title>

  <link rel="stylesheet" href="../media/CSS/bootstrap.min.css"/>
  <link rel="stylesheet" href="../media/CSS/index.css"/>
  <link rel="stylesheet" href="../media/CSS/home.css"/>
</head>
<body>
<div id="toptb">
  <div class="topline">
    <a href="#" onclick="addFavorite()">收藏本站</a>
    <a href="http://weibo.com/u/5844156822" target="_blank">官方微博</a>
  </div>
</div>
<!--导航登录-->
<div id="hd">
  <div class="logo">
    <a href="/"><img src="../img/logo.png"/></a>
  </div>
  <div class="userpanel">
    <%@ include file="../loginform.jsp"%>
      </div>

</div>

<div id="pt">
  <a href="#" name="homepage"><span class="glyphicon glyphicon-home"></span></a>
</div>
<div id="main">
  <div class="addOneBoard">
    <form class="tianjiaboard" id="tianjiaboard" name="tianjiaboard">
      <div class="form-horizontal">
        <label for="board_name">版块名称：</label>
        <input type="text" class="form-control " id="board_name"
               name="board_name" required>
        <br/>
        <label for="board_des">版块描述：</label>
          <input type="text" class="form-control " id="board_des"  name="board_des"/>

        <input type="submit" value="添加版块">
      </div>
    </form>
  </div>
</div>

<!--引入页脚-->
<%@ include file="../foot.jsp"%>
</body>
</html>
<script src="../media/JS/jquery.min.js"></script>
<script src="../media/JS/bootstrap.min.js"></script>
<script src="../media/JS/index.js"></script>
<script src="../media/JS/jquery.validate.min.js"></script>
<script src="../media/JS/addBoard.js"></script>
<script type="text/javascript" src="../media/JS/jquery.md5.js"></script>