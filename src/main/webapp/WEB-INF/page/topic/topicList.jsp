<%--
  Created by IntelliJ IDEA.
  User: Revol
  Date: 2015/12/16
  Time: 8:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.ballfuns.entity.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"  isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
  <title>JRs---welcome to basketballfuns home!!</title>

  <link rel="stylesheet" href="../media/CSS/bootstrap.min.css"/>
  <link rel="stylesheet" href="../media/CSS/index.css"/>
  <link rel="stylesheet" href="../media/CSS/board.css"/>
  <link rel="stylesheet" href="../media/CSS/topicList.css"/>
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
  <a href="/" name="homepage"><span class="glyphicon glyphicon-home"></span></a>
</div>
<div id="main">
  <div class="board_title">
    <c:if test="${board_id==1}">
      <img src="../img/NBA.png" class="topicHeadPic">
    </c:if>
    <c:if test="${board_id==2}">
      <img src="../img/muscle.jpg" class="topicHeadPic">
    </c:if>
    <c:if test="${board_id==3}">
      <img src="../img/afterGame.jpg" class="topicHeadPic">
    </c:if>
<c:if test="${board_id==4}">
  <img src="../img/freestyle.jpg" class="topicHeadPic">
</c:if>
    <c:if test="${board_id==5}">
      <img src="../img/AJSHOE.jpg" class="topicHeadPic">
    </c:if>
  </div>
  <div class="selfnav">
    <button type="button" class="btn btn-primary" onclick="toAddnewpost()">发&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;帖</button>
    <a href="/topic/reBoard">返回</a>
    <div></div>
  </div>
  <div class="board_main">
    <c:if test="${!empty StickyList}">
      <table class="stickyList" width="100%">
        <c:forEach items="${StickyList}" var="StickyList">
          <tr>
            <td>
              <span class="sticky">置顶</span>
              <c:if test="${StickyList.digest==1}"><span class="digest">精华</span></c:if>
              <a href="/post/${StickyList.topic_id}" target="_blank">    ${StickyList.topic_title}    </a>
            </td>
            <td>${StickyList.user_name}</td>
            <td>${StickyList.topic_replies}</td>
            <td>${StickyList.topic_views}</td>
            <td>${StickyList.last_post}</td>
          </tr>
        </c:forEach>
      </table>
    </c:if>
    <br/><!---分割线-->
    <c:if test="${!empty topicList}">
      <table>
        <tr>
          <th width="685px">主题</th>
          <th width="92px">作者</th>
          <td width="30px" class="huifuliulan">回复</td>
          <td width="30px" class="huifuliulan">浏览</td>
          <th width="200px">最后发表</th>
        </tr>
      </table>


    <table id="board_table" class="topics" width="100%">
        <c:forEach items="${topicList}" var="alltopics">
          <tr class="topicTR">
            <td>  <c:if test="${alltopics.digest==1}"><span class="digest">【精华】</span></c:if>
              <a href="/post/${alltopics.topic_id}" target="_blank">    ${alltopics.topic_title}    </a></td>
            <td>${alltopics.user_name}</td>
            <td>${alltopics.topic_replies}</td>
            <td>/${alltopics.topic_views}</td>
            <td>${alltopics.last_post}</td>
          </tr>
        </c:forEach>
    </table>
    </c:if>
    <!--分页的lll-->
    <div>
      <div class="badoo">
        <ul class="pagination">
          <li>
            <span class="disabled">第${pageModel.page}页/共${pageModel.pageCount}页</span>
          </li>
          <li>
            <a href="${pageuri}?page=1">首页</a>
          </li>
          <li>
            <c:if test="${pageModel.page>1}">
              <a href="${pageuri}?page=${pageModel.pevpage}">上一页</a>
            </c:if>
          </li>
          <li>
            <c:forEach var="pre" items="${pageModel.prevPages }">
              <a href="${pageuri}?page=${pre }">${pre}</a>
            </c:forEach>
          </li>
        <li class="active">
          <span class="current">${pageModel.page }</span>
        </li>
        <li>
          <c:forEach var="next" items="${pageModel.nextPages }">
            <a href="${pageuri}?page=${next }">${next}</a>
          </c:forEach>
        </li>
         <li>
           <c:if test="${pageModel.page<pageModel.lastpage}">
             <a href="${pageuri}?page=${pageModel.nextpage}">下一页</a>
           </c:if>
         </li>
<li>
  <a href="${pageuri}?page=${pageModel.lastpage}">尾页</a>
</li>

        </ul>

      </div>
    </div>
  </div>

</div>
<hr style="margin-top: 2px" />
<!--引入页脚-->
<%@ include file="../foot.jsp"%>

</body>
</html>
<script src="../media/JS/jquery.min.js"></script>
<script src="../media/JS/bootstrap.min.js"></script>
<script src="../media/JS/index.js"></script>
<script src="../media/JS/topicList.js"></script>
<script type="text/javascript" src="../media/JS/jquery.md5.js"></script>