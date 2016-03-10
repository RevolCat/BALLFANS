<%@ page import="com.ballfuns.entity.User" %>
<%--
  Created by IntelliJ IDEA.
  User: Revol
  Date: 2015/12/20
  Time: 17:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<%
  if(session.getAttribute("sessionusername")==null){
    out.print("<div>");
    out.print(" <div class=\"loginform \" >");
    out.print("<div class=\"usernameandpassword\">");
    out.print("<span>账&nbsp;&nbsp;&nbsp;号");
    out.print("</span>");
    out.print("<input type=\"text\" id=\"user_account\" name=\"user_account\"/>");
    out.print("<br/>");
    out.print("<span>密&nbsp;&nbsp;&nbsp;码");
    out.print("</span>");
    out.print("<input type=\"password\" id=\"password\" name=\"password\" >");
    out.print("</div>");
    out.print("<button type=\"button\" class=\"btn btn-default denglu \" onclick=\"login()\">登录");
    out.print("</button>");
    out.print("</div>");
    out.print("</form>");
    out.print("<div class=\"rowline\">");
    out.print("</div>");
    out.print("<div class=\"register\">");
    out.print(" <a href=\"/user/registerweb\">立即注册");
    out.print("</a>");
    out.print("   </div>");
    out.print("   </div>");
  }else {
 User user=(User) request.getSession().getAttribute("sessionUser");
 // String loginname=request.getSession().getAttribute("sessionusername").toString();
 //  String  usercredit=request.getSession().getAttribute("sessioncredit").toString();

  // Integer userId=user.getUser_id();
    String userAccount=user.getUser_account();
   String loginname=user.getUser_name();
   Integer usercredit=user.getCredit();
    Integer usertype=user.getUser_type();
    out.print("当前用户:<a href=\"/user/personInfo\">"+loginname);
    out.print("</a>");
    out.print("&nbsp;&nbsp;积分:"+usercredit);
      //out.print("&nbsp;&nbsp;用户类型:");
      out.print("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href=\"/user/exit\">exit");
    out.print("    </a><br/>");
      out.print("");
      out.print("");
      out.print("");
      out.print("");


/*
*   out.print("<c:if test=\"${requestScope.user.usertype}==3\">");
      out.print("  <a href=\"/user/toManager\">管理用户");
     out.print("</a>");
     out.print("</c:if>");
* */


    out.print("");
    out.print("");
    out.print("");
    out.print("");
    out.print("");
    out.print("");
  }
%>

</body>
</html>
