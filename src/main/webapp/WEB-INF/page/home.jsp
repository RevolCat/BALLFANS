<%--
  Created by IntelliJ IDEA.
  User: Revol
  Date: 2015/12/15
  Time: 15:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.ballfuns.entity.User" %>

<%@ page contentType="text/html;charset=UTF-8" language="java"   isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
  <title>JR---welcome to basketballfuns home!!</title>
  <script src="../media/JS/jquery.min.js"></script>
  <script src="../media/JS/bootstrap.min.js"></script>
  <script src="../media/JS/home.js"></script>
  <script src="../media/JS/index.js"></script>
  <link rel="stylesheet" href="../media/CSS/bootstrap.min.css"/>
  <link rel="stylesheet" href="../media/CSS/index.css"/>
  <link rel="stylesheet" href="../media/CSS/home.css"/>
</head>
<body >
<!--顶层工具条-->
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
    <%@ include file="loginform.jsp"%>
  </div>

</div>
</div>
<div id="pt">
  <a href="/" name="homepage"><span class="glyphicon glyphicon-home"></span></a>
</div>

<div id="TheMain">

<c:if test="${!empty AllBoard}">
    <c:forEach items="${AllBoard}" var="allboard">
      <a href="/topic/${allboard.board_id}">
          <div class="HOMEfield">
          ${allboard.board_name}<br/>
           <span> ${allboard.board_des}</span>
              <br/>
              <span>主题数：${allboard.topic_num}</span>
          </div>
        </a>
    </c:forEach>
</c:if>
    <br/>

  </div>

<hr style="margin-top: 2px" />
<hr style="margin-top: 2px" />
<!--引入页脚-->
  <%@ include file="foot.jsp"%>

</body>
</html>
