package com.ballfuns.controller;

import com.ballfuns.entity.Page;
import com.ballfuns.entity.Post;
import com.ballfuns.entity.Topic;
import com.ballfuns.entity.User;
import com.ballfuns.service.PostService;
import com.ballfuns.service.TopicService;
import com.ballfuns.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by Revol on 2015/12/17.
 */
@Controller
@RequestMapping("/post")
public class PostController {
    private PostService postService;
    @Autowired
    @Qualifier(value = "postService")
    public void setPostService(PostService postService) {
        this.postService = postService;
    }

    private TopicService topicService;
    @Autowired
    @Qualifier(value = "topicService")
    public void setTopicService(TopicService topicService) {
        this.topicService = topicService;
    }

    private UserService userService;
    @Autowired
    @Qualifier(value = "userService")
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
     * 显示主题帖子内容and回复 并分页
     *
     */
    @RequestMapping(value = "/{topic_id}", method = RequestMethod.GET)
    public String getTopic(@PathVariable("topic_id") int topic_id,Post post, Model model,HttpServletRequest request) {
        Topic topic=topicService.getTopicById(topic_id);
        Page pageModel=new Page();
        String currentpage=(String) request.getParameter("page");
        if(currentpage!=null){
            int page=Integer.parseInt(currentpage);
            if(page!=0){
                pageModel.setPage(page);
            }
        }
        else{
            pageModel.setPage(1);
        }

        pageModel.setPageSize(13);
        pageModel.setTotalCount(postService.getPostByTopicId(topic_id, topic).size());
        pageModel.setNum(3);

        List<Post> postList=postService.getPostByTopicIdPage(topic_id,topic,
                pageModel.getPageSize(),pageModel.getPage());
        pageModel.setList(postList);

        request.setAttribute("thepost", postList);
        request.setAttribute("count", postList.size());
        request.setAttribute("pageModel",pageModel);
        request.setAttribute("page", pageModel.getPage());


        model.addAttribute("thetopic", topicService.getTopicById(topic_id));
        //model.addAttribute("thepost", postService.getPostByTopicId(topic_id,topic));
      //  request.getSession(true).setAttribute("boardId", board_id);
        request.getSession(true).setAttribute("topicId",topic_id);

        //获取回复数
        int reply=postService.getPostByTopicId(topic_id,topic).size();
        System.out.println("THE REPLYS=======================+" + reply);
        topicService.addTopicReply(topic, reply);
        return "/post/postList";
    }

    //发表时检测是否存在用户
    @RequestMapping("/checkUser")
    public void checkUser(HttpServletRequest request,HttpServletResponse response) throws Exception{
        Integer  currentUserId=(Integer)request.getSession().getAttribute("sessionuserId");
        PrintWriter pw=null;
        pw=response.getWriter();
        if(currentUserId==null){
            pw.print("{\"result\":\"0\"}");
        }else{
            pw.print("{\"result\":\"1\"}");
        }
    }

    //发表回复
    @RequestMapping("/pushPost")
    public String pushPost(Topic topic,Post post,User user,HttpServletRequest request,RedirectAttributes redirectAttributes){
        Integer topicId=(Integer) request.getSession().getAttribute("topicId");
        Integer  currentUserId=(Integer)request.getSession().getAttribute("sessionuserId");
        String currentUserName=request.getSession().getAttribute("sessionusername").toString();
        User currentUser=userService.getByName(currentUserName);

        postService.addPost(post, topicId, currentUserId, currentUserName);
        // 用户积分增加
        userService.addPushPostCredit(currentUser);

        Topic currentTopic=topicService.getTopicById(topicId);
        //topicService.addTopicReply(currentTopic);
        topicService.addTopiclastTime(currentTopic);
        //R重定向
        redirectAttributes.addAttribute("topic_id",post.getTopic_id()).addFlashAttribute("message", "Account created!");
        if((Integer) request.getSession().getAttribute("sessionusertype")!=0){
         return "redirect:/post/m/{topic_id}";
        }
        else{
            return "redirect:/post/{topic_id}";
        }

    }


    /**
     *
     * 管理回复帖子
     *
     * */


    //回复帖子，并分页
    @RequestMapping(value = "/m/{topic_id}", method = RequestMethod.GET)
    public String MgetTopic(@PathVariable("topic_id") int topic_id,Post post, Model model,HttpServletRequest request) {
        Topic topic=topicService.getTopicById(topic_id);
        Page pageModel=new Page();
        String currentpage=(String) request.getParameter("page");
        if(currentpage!=null){
            int page=Integer.parseInt(currentpage);
            if(page!=0){
                pageModel.setPage(page);
            }
        }
        else{
            pageModel.setPage(1);
        }

        pageModel.setPageSize(10);
        pageModel.setTotalCount(postService.getPostByTopicId(topic_id, topic).size());
        pageModel.setNum(3);

        List<Post> postList=postService.getPostByTopicIdPage(topic_id,topic,
                pageModel.getPageSize(),pageModel.getPage());
        pageModel.setList(postList);

        request.setAttribute("thepost",postList);
        request.setAttribute("count",postList.size());
        request.setAttribute("pageModel", pageModel);
        request.setAttribute("page", pageModel.getPage());


        model.addAttribute("thetopic", topicService.getTopicById(topic_id));

        //获取回复数
        int reply=postService.getPostByTopicId(topic_id,topic).size();
        System.out.println("THE REPLYS=======================+" + reply);
        topicService.addTopicReply(topic, reply);
        request.getSession(true).setAttribute("topicId", topic_id);
        return "/manager/post/postList";
    }


    //删除回复
    @RequestMapping("/m/deletePost")
    public String MdeletePost(Topic topic,Post post,int post_id,HttpServletRequest request,RedirectAttributes redirectAttributes){
        postService.deletePostByPostID(post_id);

        String currentTopicID=request.getSession().getAttribute("topicId").toString();
        int ctopicId=(Integer) request.getSession().getAttribute("topicId");
        redirectAttributes.addAttribute("topic_id",currentTopicID).addFlashAttribute("message", "Account created!");
        return "redirect:/post/m/{topic_id}";
    }

    //设置精华帖
    @RequestMapping("/m/setDigest")
    public String MsetDigest(int topic_id,RedirectAttributes redirectAttributes,HttpServletRequest request){
        topicService.setDigest(topic_id);
        String currentTopicID=request.getSession().getAttribute("topicId").toString();
        redirectAttributes.addAttribute("topic_id",currentTopicID).addFlashAttribute("message", "Account created!");
        return "redirect:/post/m/{topic_id}";
    }

    //取消精华帖
    @RequestMapping("/m/offDigest")
    public String MoffDigest(int topic_id,RedirectAttributes redirectAttributes,HttpServletRequest request){
        topicService.offDigest(topic_id);
        String currentTopicID=request.getSession().getAttribute("topicId").toString();
        redirectAttributes.addAttribute("topic_id",currentTopicID).addFlashAttribute("message", "Account created!");
        return "redirect:/post/m/{topic_id}";
    }
}