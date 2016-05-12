<%--
  Created by IntelliJ IDEA.
  User: Revol
  Date: 2015/12/23
  Time: 10:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>JRs---welcome to basketballfuns home!!</title>

  <link rel="stylesheet" href="../media/CSS/bootstrap.min.css"/>
  <link rel="stylesheet" href="../media/CSS/index.css"/>
  <link rel="stylesheet" href="../media/CSS/board.css"/>
  <link rel="stylesheet" href="../media/CSS/pushTopic.css">
</head>
<body>
<div id="toptb">
  <div class="topline">
    <a href="#" onclick="addFavorite()">收藏本站</a>
    <a href="http://weibo.com/u/5844156822" target="_blank">官方微博</a>
  </div>
</div>
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
<!--
<input type="hidden" id="sessionusername" value="<%=request.getSession().getAttribute("sessionusername")%>"/>
-->

<div class="main">
  <div class="fabiaoxintie">
    <form class="fabiao" id="fabiao" name="fabiao">
      <div class="form-horizontal">
        <label for="topic_title">标题：</label>
          <input type="text" class="form-control " id="topic_title"
                  name="topic_title" required>
<br/>
        <label for="topic_text">内容：</label>
          <textarea class="form-control " id="topic_text" rows="10" cols="30" name="topic_text"
                    maxlength="500" required></textarea>

        <input type="submit" value="发表帖子">
      </div>
    </form>
  </div>
</div>
<hr style="margin-top: 2px" />
<%@ include file="../foot.jsp"%>
</body>
</html>
<script src="../media/JS/jquery.min.js"></script>
<script src="../media/JS/jquery.validate.min.js"></script>
<script src="../media/JS/bootstrap.min.js"></script>
<script src="../media/JS/index.js"></script>
<script src="../media/JS/pushTopic.js"></script>
<script type="text/javascript" src="../media/JS/jquery.md5.js"></script>