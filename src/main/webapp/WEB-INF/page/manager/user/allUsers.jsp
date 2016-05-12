<%--
  Created by IntelliJ IDEA.
  User: Revol
  Date: 2015/12/15
  Time: 15:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"   isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
  <title>JR---welcome to basketballfuns home!!</title>

  <link rel="stylesheet" href="../../media/CSS/bootstrap.min.css"/>
  <link rel="stylesheet" href="../../media/CSS/bootstrap-switch.min.css"/>
  <link rel="stylesheet" href="../../media/CSS/index.css"/>
  <link rel="stylesheet" href="../../media/CSS/home.css"/>
  <link rel="stylesheet" href="../../media/CSS/allUsers.css"/>
</head>
<body>
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
    <a href="/roothome"><img src="../img/logo.png"/></a>
  </div>
  <div class="userpanel">
    <%@ include file="../../loginform.jsp"%>

  </div>

</div>
</div>
<div id="pt">
  <a href="#" name="homepage"><span class="glyphicon glyphicon-home"></span></a>
</div>

<div id="main">
  <c:if test="${!empty userList}">
    <table id="board_table"  width="100%">
      <th>
        <tr>
          <th>ID</th>
          <th>用户名</th>
          <th>用户种类</th>
          <th>是否锁定</th>
          <th>积分</th>
          <th>性别</th>
          <th>注册时间</th>

          <th>删除</th>
        </tr>
      </th>
      <c:forEach items="${userList}" var="userList">
        <tr>
          <td>  ${userList.user_id}   </td>
          <td>${userList.user_name}</td>
          <td>
            <c:if test="${userList.user_type==3}">超级管理员</c:if>
            <c:if test="${userList.user_type==2}">管理员&nbsp;<button onclick="offManager(${userList.user_id})" class="btn btn-danger btn-sm"> 取消管理权限</button></c:if>
            <c:if test="${userList.user_type==0}">会员&nbsp;<button onclick="setManager(${userList.user_id})" class="btn btn-primary btn-sm"> 设为管理</button> </c:if>
          </td>
          <td >
          <c:if test="${userList.locked==1}">
            <span class="unNormalUser">已锁定</span>
          </c:if>
            <c:if test="${userList.locked==0}">
              <span class="normalUser">正常使用</span>
            </c:if>
            <a href="/user/lockUser?user_id=${userList.user_id}" onclick="lockUser()" oncClientClick="return lockUser()">锁定</a>
             <a href="/user/unlockUser?user_id=${userList.user_id}" onclick="UnlockUser()">解锁</a>

          </td>
          <td>${userList.credit}</td>
          <td>${userList.sex}</td>
          <td>${userList.register_date}</td>

          <td><a href="/user/deleteUser?user_id=${userList.user_id}">删除</a></td>
        </tr>
      </c:forEach>
    </table>
  </c:if>


  <div>
    <div class="badoo">  <ul class="pagination">
   <li><span class="disabled">第${pageModel.page}页/共${pageModel.pageCount}页</span></li>

        <li ><a href="${pageuri}?page=1">首页</a></li>

        <c:if test="${pageModel.page>1}">
        <li>  <a href="${pageuri}?page=${pageModel.pevpage}">上一页</a></li>
        </c:if>

        <c:forEach var="pre" items="${pageModel.prevPages }">
       <li>
         <a href="${pageuri}?page=${pre }">${pre}</a>
       </li>
        </c:forEach>

       <li class="active">
         <span class="current">${pageModel.page }</span>
       </li>

        <c:forEach var="next" items="${pageModel.nextPages }">
         <li>
           <a href="${pageuri}?page=${next }">${next}</a>
         </li>
        </c:forEach>

        <c:if test="${pageModel.page<pageModel.lastpage}">
         <li><a href="${pageuri}?page=${pageModel.nextpage}">下一页</a></li>
        </c:if>


   <li> <a href="${pageuri}?page=${pageModel.lastpage}">尾页</a></li>
      </ul>
    </div>
  </div>
  <br/>

</div>

<hr style="margin-top: 2px" />
<hr style="margin-top: 2px" />
<!--引入页脚-->
<%@ include file="../../foot.jsp"%>

</body>
</html>
<script src="../../media/JS/jquery.min.js"></script>
<script src="../../media/JS/bootstrap.min.js"></script>
<script src="../../media/JS/bootstrap-switch.min.js"></script>
<script src="../../media/JS/index.js"></script>
<script src="../../media/JS/allUsers.js"></script>
<script src="../../media/JS/roothomecheck.js"></script>
<script type="text/javascript" src="../media/JS/jquery.md5.js"></script>
