package com.ballfuns.controller;

import com.ballfuns.entity.Page;
import com.ballfuns.entity.User;
import com.ballfuns.service.TopicService;
import com.ballfuns.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by Revol on 2015/12/18.
 */
@Controller
@RequestMapping("/user")
public class UserController {
    private UserService userService;
@Autowired
@Qualifier(value = "userService")
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    private TopicService topicService;
@Autowired
@Qualifier(value = "topicService")
    public void setTopicService(TopicService topicService) {
        this.topicService = topicService;
    }



    //跳转注册页面
    @RequestMapping("/registerweb")
    public String toaddUser(User user){
        return "/user/register";
    }

    //注册功能
    @RequestMapping("/register")
    public String addUser(User user,HttpServletRequest request){
        userService.addUser(user);
        System.out.println("user info" + user);
        String account=request.getParameter("user_account");
        User dbuser=userService.getByAccount(account);
        request.getSession(true).setAttribute("sessionUser",dbuser);
        request.getSession(true).setAttribute("sessionuserId", dbuser.getUser_id());
        request.getSession(true).setAttribute("sessionuserAccount", dbuser.getUser_account());
        request.getSession(true).setAttribute("sessionusername", dbuser.getUser_name());
        request.getSession(true).setAttribute("sessioncredit", dbuser.getCredit());
        request.getSession(true).setAttribute("sessionusertype", dbuser.getUser_type());
        return "/user/success";
    }

    //检测账号
    @RequestMapping(value = "/checkAccount",method = RequestMethod.POST)
    public void checkAccount(HttpServletRequest request,HttpServletResponse response)throws  Exception{
        String yonghu=request.getParameter("user_account");
        System.out.println("获取的参数" + yonghu);
        PrintWriter pw=null;
        System.out.println("获取的对象" + userService);
        pw=response.getWriter();
        if(this.userService.getByAccount(yonghu)==null){
            pw.print("{\"result\":\"1\"}");
        }else {
            pw.print("{\"result\":\"2\"}");
        }
        pw.flush();
        pw.close();
    }



    //检测用户名
    @RequestMapping(value = "/checkName",method = RequestMethod.POST)
    public void checkName(HttpServletRequest request,HttpServletResponse response)
    throws Exception{
        String yonghuming=request.getParameter("user_name");
        System.out.println("获取的参数"+yonghuming);
        PrintWriter pw=null;
        System.out.println("获取的对象"+userService);
        pw=response.getWriter();
        if(this.userService.getByName(yonghuming)==null){
            //返回的json
            pw.print("{\"result\":\"3\"}");
        }else {
            pw.print("{\"result\":\"4\"}");
        }
        pw.flush();
        pw.close();
    }



    //登录
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public void UserLogin(HttpServletRequest request,HttpServletResponse response,User user) throws  Exception{
        String account=request.getParameter("user_account");
        String pwd=request.getParameter("password");
        PrintWriter pw=null;
        pw=response.getWriter();
        System.out.println("获取的参数" + account);
        System.out.println("获取的参数" + pwd);
        //用过账号查询用户
        User dbuser=userService.getByAccount(account);
        request.setAttribute("currentUser", dbuser);
        if(dbuser==null){
            pw.print("{\"result\":\"5\"}");
        }
        else if(!pwd.equals(dbuser.getPassword())){
            pw.print("{\"result\":\"6\"}");
            System.out.println("查到的密码"+dbuser.getPassword());
        }
        else if(dbuser.getLocked()==1){
            pw.print("{\"result\":\"7\"}");
            System.out.println("用户锁定了吗？" + dbuser.getLocked());
        }
        else{
          if(dbuser.getUser_type()==0){
              pw.print("{\"result\":\"8\"}");
          }else if(dbuser.getUser_type()==2){
              pw.print("{\"result\":\"9\"}");
              request.getSession(true).setAttribute("managerID",dbuser.getUser_id());
          }
            else if(dbuser.getUser_type()==3){
              pw.print("{\"result\":\"10\"}");
          }

            //登录积分+1
            userService.addLoginCredit(dbuser);
            System.out.println("SUCCESSSSSSSSSSSSSSSSSSSSS             " + dbuser.getCredit());
            request.getSession(true).setAttribute("sessionUser",dbuser);
           request.getSession(true).setAttribute("sessionuserId", dbuser.getUser_id());
            request.getSession(true).setAttribute("sessionuserAccount", dbuser.getUser_account());
            request.getSession(true).setAttribute("sessionusername", dbuser.getUser_name());
            request.getSession(true).setAttribute("sessioncredit", dbuser.getCredit());
           request.getSession(true).setAttribute("sessionusertype", dbuser.getUser_type());
            System.out.println("当前     " + dbuser.getUser_name()+"当前account     "+dbuser.getUser_account());
            //获取客户端IP
            System.out.println(request.getRemoteAddr());
            /**  //获取创建session时间
             HttpSession session=request.getSession();
             System.out.println(  session.getCreationTime());
             * 设置存在时间(s)
             * HttpSession session=request.getSession();
             session.setMaxInactiveInterval(120);
             * */
        }

    }


    //退出
    @RequestMapping("/exit")
    public  String UserExit(HttpSession session){
        session.removeAttribute("sessionusername");
        session.invalidate();
        return "redirect:/";
    }

    //个人信息
    @RequestMapping("/personInfo")
    public String PersonInfo(HttpServletRequest request) {
        String  currentName=request.getSession().getAttribute("sessionusername").toString();
        //userService.getByName(currentName);
        System.out.println("获取的session's name？" + currentName);
        return "/user/personInfo";
    }

    /**
     * 发表的主题
     @RequestMapping("/topic")
     public String UserTopic(User user,HttpServletRequest request,Model model){
     String currentName=request.getSession().getAttribute("sessionusername").toString();
     System.out.println("获取的session's account？" + currentName);
     model.addAttribute("userTopicList", topicService.getTopicByUserName(currentName));
     return "redirect:/user/personInfo";
     }
     *
     * */


    //修改密码。检测当前密码
    @RequestMapping(value = "/pwd",method = RequestMethod.POST)
    public  void checkPwd(HttpServletRequest request,HttpServletResponse response) throws Exception{
        String  currentpassword=request.getParameter("currentpassword");
        String currentAccount=request.getSession().getAttribute("sessionuserAccount").toString();
        System.out.println("获取的参数" + currentpassword);
        //System.out.println("获取的参数" + currentAccount);
        PrintWriter pw=null;
        pw=response.getWriter();
        User currentuser=userService.getByAccount(currentAccount);
        if(!currentpassword.equals(currentuser.getPassword())){
            pw.print("{\"result\":\"6\"}");
            System.out.println("查到的密码"+currentuser.getPassword());
        }
        else {
            pw.print("{\"result\":\"44\"}");
        }
    }

    //更新密码
    @RequestMapping(value = "/updatepwd",method = RequestMethod.POST)
    public  void  updatePwd(User user,HttpServletRequest request,HttpServletResponse response) throws Exception{
        String currentAccount=request.getSession().getAttribute("sessionuserAccount").toString();
        System.out.println("当前用户？    "+currentAccount);
        User currentUser=userService.getByAccount(currentAccount);
        String password=request.getParameter("password");
        System.out.println("获取的新密码？  "+password);
        userService.updatePwd(currentUser,password);
        PrintWriter pw=null;
        pw=response.getWriter();
        pw.print("{\"result\":\"9999\"}");
    }



    /**
     *
     * 管理员界面
     *
     *
     *
     * */
    //跳转用户管理界面用户列表
    @RequestMapping(value = "/toManager")
    public String toManagerUser(User user,HttpServletRequest request){
        Page pageModel=new Page();
        //获取当前页
        String currentpage = (String) request.getParameter("page");
        if(currentpage!=null){
            int page=Integer.parseInt(currentpage);
            if(page!=0){
                pageModel.setPage(page);
            }
        }else{
            pageModel.setPage(1);
        }
        String hql="from User";
        //设置每页多少记录
        pageModel.setPageSize(9);
        //总记录数
        pageModel.setTotalCount(userService.getAllUserList(user).size());
        //当前页之前  显示的页数
        pageModel.setNum(2);

        List<User> userList=userService.getAllUser(hql,user,pageModel.getPageSize(),pageModel.getPage());
        pageModel.setList(userList);
        request.setAttribute("userList", userList);
        request.setAttribute("count", userList.size());
        request.setAttribute("pageModel", pageModel);
        request.setAttribute("page", pageModel.getPage());


        //??????
            String obj = request.getQueryString();
        System.out.println("obj:" + obj);
        if (obj!=null) {
            String[] fenge = obj.split("[ = & ]");
            String op = "&";
            for(int i=0;i<fenge.length;i++){
                if ("page".equals(fenge[i])) {
                    op+=fenge[i]+"="+fenge[++i];
                    break;
                }
            }
            obj = obj.replace(op, "");
        }else{
            obj = "";
        }

        return "/manager/user/allUsers";
    }

    //删除用户
    @RequestMapping("/deleteUser")
    public String deleteUser(int user_id){
      userService.deleteUserById(user_id);
        return "redirect:/user/toManager";
    }



    //锁定用户？
    @RequestMapping("/lockUser")
    public String  lockedUser(int user_id){
        userService.lockUserById(user_id);
        return "redirect:/user/toManager";
    }

    //解锁用户
    @RequestMapping("/unlockUser")
    public  String  unlockUser(int user_id){
        userService.unlockUserById(user_id);
        return "redirect:/user/toManager";
    }

    //用户管理权限控制
    @RequestMapping(value = "/checkUserType",method = RequestMethod.POST)
    public void checkUserType(HttpServletRequest request,HttpServletResponse response) throws Exception{
        Integer currentUserType=(Integer) request.getSession().getAttribute("sessionusertype");
        PrintWriter pw=null;
        pw=response.getWriter();
        if(currentUserType!=3){
            //无权限
            pw.print("{\"result\":\"100\"}");
            System.out.println("当前种类 =="+currentUserType);
        }
        else{
            pw.print("{\"result\":\"101\"}");
        }
    }

//板块管理权限控制
    @RequestMapping(value = "/checkUserTypeTopic",method = RequestMethod.POST)
    public void checkManagerTopicPower(HttpServletRequest request,HttpServletResponse response) throws Exception{
        Integer currentUserType=(Integer) request.getSession().getAttribute("sessionusertype");
        PrintWriter pw=null;
        pw=response.getWriter();
        if(currentUserType==null){
            pw.print("{\"result\":\"202\"}");
        }
        else if(currentUserType==0){
            //无权限
            pw.print("{\"result\":\"200\"}");
            System.out.println("当前种类 =="+currentUserType);
        }
        else {
            pw.print("{\"result\":\"201\"}");
        }
    }

//检测跳转到的主页
@RequestMapping(value = "/checkPower",method = RequestMethod.POST)
public void checkPower(HttpServletRequest request,HttpServletResponse response) throws Exception{
    Integer currentUserType=(Integer) request.getSession().getAttribute("sessionusertype");
    PrintWriter pw=null;
    pw=response.getWriter();
    if(currentUserType==0){
        //无权限
        pw.print("{\"result\":\"200\"}");
        System.out.println("当前种类 =="+currentUserType);
    }
    else if (currentUserType==2){
        pw.print("{\"result\":\"202\"}");
    }
    else if(currentUserType==3){
        pw.print("{\"result\":\"203\"}");

    }
}

    //设为管理员
    @RequestMapping("/m/setManager")
    public String setManager(int user_id){
        userService.setManager(user_id);
        return "redirect:/user/toManager";
    }

    //取消管理员
    @RequestMapping("/m/offManager")
    public String offManager(int user_id){
        userService.offManager(user_id);
        return "redirect:/user/toManager";
    }



}
