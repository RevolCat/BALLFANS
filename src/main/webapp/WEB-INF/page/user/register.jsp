<%--
  Created by IntelliJ IDEA.
  User: Revol
  Date: 2015/12/18
  Time: 12:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta charset="UTF-8">
  <title>JR---welcome to basketballfuns home!!</title>
  <link rel="stylesheet" href="../media/CSS/bootstrap.min.css"/>
  <link rel="stylesheet" href="../media/CSS/index.css"/>
  <link rel="stylesheet" href="../media/CSS/register.css"/>
  <script>

  </script>
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
  </div>
</div>
<div id="pt">
  <a href="#" name="homepage"><span class="glyphicon glyphicon-home"></span></a>
</div>
<div id="main">
  <h1>用户注册</h1>
  <!--
  水平线
  -->
  <div class="col-line"></div><br/>
  <div class="biaodan">
    <form role="form" id="regform" name="registerform" class="form-horizontal">
      <div class="form-group  ">
        <label class="control-label col-md-4 col-lg-4 col-sm-4">账号</label>
        <input type="text"  id="user_account" name="user_account"
        placeholder="输入你的账号哦，英文或者数字" required  onblur="checkAccount()"/>

        <span id="spaName" ></span>
      </div>
      <div class="form-group ">
        <label class=" control-label col-md-4 col-lg-4 col-sm-4">用户名</label>
        <input type="text" id="user_name" name="user_name"
               placeholder="输入你的大名哦" required  onblur="checkName()"/><!---->
        <span id="spaName2" ></span>
      </div>
      <div class="form-group ">
        <label class=" control-label col-md-4 col-lg-4 col-sm-4">密码</label>
        <input type="password" id="password" name="password" required/>
      </div>
      <div class="form-group ">
        <label class=" control-label col-md-4 col-lg-4 col-sm-4" >再输一次密码啊亲</label>
        <input type="password" id="password2" name="password2" required/>
      </div>
      <div class="form-group ">
        <label class=" control-label col-md-4 col-lg-4 col-sm-4" >性别</label>
        <input type="radio" name="sex" value="1" checked>男
        <input type="radio" name="sex" value="0">女
      </div>

<div class="button-group">
  <button type="submit" class="btn btn-primary " id="subbutton"  >注册</button>
  <button type="button" class="btn btn-default" onclick="reset()">重置</button>
  <a href="/"> <span class="small">（已有账号返回首页登录）</span></a>

</div>





    </form>
  </div>
</div>
<hr style="margin-top: 2px" />

<!--引入页脚-->
<%@ include file="../foot.jsp"%>
</body>
</html>
<script src="../media/JS/jquery.min.js"></script>
<script src="../media/JS/jquery.validate.min.js"></script>
<script src="../media/JS/bootstrap.min.js"></script>
<script src="../media/JS/index.js"></script>
<script type="text/javascript" src="../media/JS/registe.js"></script>
<script type="text/javascript" src="../media/JS/jquery.md5.js"></script>