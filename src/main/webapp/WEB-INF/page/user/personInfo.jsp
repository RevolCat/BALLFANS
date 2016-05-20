<%--
  Created by IntelliJ IDEA.
  User: Revol
  Date: 2015/12/21
  Time: 9:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"  isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <meta charset="UTF-8">
  <title>JR---welcome to basketballfuns home!!</title>


  <link rel="stylesheet" href="../media/CSS/bootstrap.min.css"/>
  <link rel="stylesheet" href="../media/CSS/index.css"/>
  <link rel="stylesheet" href="../media/CSS/personInfo.css"/>

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
  <div class="leftNav">
    <div class="navmenu"><a href="javascript:void(0)" onclick="changeNav1()">基本资料</a></div>
    <div class="navmenu"><a href="javascript:void(0)" onclick="changeNav2 ()">安全</a></div>
  </div>
  <div class="rightNavInfo">
    <div class="baseinformation">
      <div class="showhead">      <img src=""/>     </div>
      <div class="showinformation">
        用户名：<%=request.getSession().getAttribute("sessionusername").toString() %><br/>
        性别：<%
        User user=(User) request.getSession().getAttribute("sessionUser");
        String sex=user.getSex();
        System.out.println(sex);
        if(sex.equals("0")){
        out.print("<span>女");
        out.print("</span>");
      }else{
        out.print("<span>男");
        out.print("</span>");
      }
      %><br/>
        积分：<%=request.getSession().getAttribute("sessioncredit").toString() %><br/>
      </div>
    </div>
    <div class="personalTopic">
      <ul class="list">
        <!--
        <li>发表的主题</li>
        <li>发表的回复</li>
        -->
      </ul>
      <section class="box">
        <section class="content">
          <c:if test="${!empty userTopicList}">
            <table id="board_table"  width="100%">
              <c:forEach items="${userTopicList}" var="List">
                <tr>
                  <td>  <a href="/post/${List.topic_id}">    ${List.topic_title}    </a></td>
                  <td>${List.user_name}</td>
                  <td>${List.topic_replies}</td>
                  <td>${List.topic_views}</td>
                  <td>${List.last_post}</td>
                </tr>
              </c:forEach>
            </table>
          </c:if>
        </section>
        <section class="content">22</section>
      </section>

    </div>
  </div>
  <div class="rightNavSecurity">
    <div >
<form id="xiugaimima" name="xiugaimima">
  <label for="currentpassword">当前密码：</label>
  <input type="password" id="currentpassword" name="currentpassword" onblur="checkPwd()"/>
  <span id="spaN" ></span><br/>
  <label for="editpassword">新密码：</label>
  <input type="password" id="editpassword" name="password" onblur="checkLength()">
  <span id="spaN2"></span><br/>
  <label for="cofirmpassword">再次确认：</label>
  <input type="password" id="cofirmpassword" name="confirm_again" onblur="checkConfirm()">
  <span id="spaN3"></span><br/>
  <input type="submit" id="subbutton" value="确认修改" >&nbsp;&nbsp;
  <button type="reset" >重置</button>
</form>
    </div>
  </div>

</div>
<hr style="margin-top: 2px" />

<%@ include file="../foot.jsp"%>
</body>

</html>
<script src="../media/JS/jquery.min.js"></script>
<script src="../media/JS/jquery.validate.min.js"></script>
<script src="../media/JS/bootstrap.min.js"></script>
<script src="../media/JS/personInfo.js"></script>
<script type="text/javascript" src="../media/JS/jquery.md5.js"></script>