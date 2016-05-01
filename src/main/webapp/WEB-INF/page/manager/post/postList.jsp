<%--
  Created by IntelliJ IDEA.
  User: Revol
  Date: 2015/12/17
  Time: 15:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"  isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
  <meta charset="UTF-8">
  <title>JR---welcome to basketballfuns home!!</title>
  <script src="../../media/JS/jquery.min.js"></script>
  <script src="../../media/JS/jquery.validate.min.js"></script>
  <script src="../../media/JS/bootstrap.min.js"></script>
  <script src="../../media/JS/index.js"></script>
  <script src="../../media/JS/postList.js"></script>
  <script src="../../media/JS/managerTopicCheck.js"></script>

  <link rel="stylesheet" href="../../media/CSS/postList.css"/>
  <link rel="stylesheet" href="../../media/CSS/bootstrap.min.css"/>
  <link rel="stylesheet" href="../../media/CSS/index.css"/>
</head>
<body>
<!--顶层工具条-->
<div id="toptb">
  <div class="topline">
    <a href="#" onclick="addFavorite()">收藏本站</a>
    <a href="http://weibo.com/u/5844156822" target="_blank">官方微博</a>
  </div>
</div>

<div id="hd">
  <div class="logo">
    <a href="/"><img src="../../img/logo.png"/></a>
  </div>
  <div class="userpanel">
    <%@ include file="../../loginform.jsp"%>
  </div>
</div>

<div id="pt">
  <a href="/" name="homepage"><span class="glyphicon glyphicon-home"></span></a>
</div>

<div id="main">
  <div class="selfnav">

    <button type="button" class="btn btn-info">回&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;复</button>
    <a href="/topic/m/re">返回</a>

  </div>
  <hr/>
<div class="postlist">
  <div class="ptopic">
    <c:if test="${thetopic.digest==1}">
      <span class="digest">精华</span>
    </c:if>
    <span class="topicTitle">${thetopic.topic_title}</span>
    <table  class="postTable" width="100%">
      <tr>
        <td class="postAuthor">${thetopic.user_name}</td>
        <td>
          <span class="postTime">发布于：${thetopic.create_time}</span><br/>
          <div class="postText">
            ${thetopic.topic_text}
          </div>
        </td>
        <td>
          <c:if test="${thetopic.digest==0}">
            <button onclick="setDigest(${thetopic.topic_id})" class="btn btn-primary">设为精华</button>
          </c:if>
          <c:if test="${thetopic.digest==1}">
            <button onclick="offDigest(${thetopic.topic_id})" class="btn btn-danger">取消精华</button>
          </c:if>
        </td>
      </tr>

    </table>

  </div>

<c:if test="${!empty thepost}">
<c:forEach items="${thepost}" var="allpost">
  <div class="post">
    <table class="postTable" width="100%">
      <tr>
        <td class="postAuthor">${allpost.user_name}</td>
        <td>
          <span class="postTime">发布于：${allpost.create_time}</span><br/>
          <div class="postText">
              ${allpost.post_text}
          </div>
        </td>
        <td >
          <div class="postButton">
            <button  onclick="deletePost(${allpost.post_id})" class="btn btn-danger">删除</button>
          </div>

        </td>
      </tr>
    </table>

  </div>
</c:forEach>
</c:if>
  <div class="fenye">
    <div class="badoo">
      <!--无评论显示-->
      <c:if test="${pageModel.pageCount==0}">
        <div class="comment">
          <h4><span> 目前无评论</span></h4>
        </div>
      </c:if>
      <!--有评论显示-->
      <c:if test="${pageModel.pageCount!=0}">
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
      </c:if>


    </div>
  </div>


</div>
  <hr style="margin-top: 2px" />
  <div class="postTopic">
    <form id="pushPost">
      <div class="form-horizontal">
        <label for="post_text">发表回复</label>
        <br/>
        <textarea  rows=5" cols="30" name="post_text"  id="post_text"></textarea><br/>
        <input type="submit" value="回复" >
      </div>

    </form>
  </div>
</div>

<hr style="margin-top: 2px" />
<%@include file="../../foot.jsp"%>
</body>
</html>
