package com.ballfuns.controller;

import com.ballfuns.entity.Board;
import com.ballfuns.entity.Page;
import com.ballfuns.entity.Topic;
import com.ballfuns.entity.User;
import com.ballfuns.service.*;
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
 * Created by Revol on 2015/12/16.
 */
@Controller
@RequestMapping("/topic")
public class TopicController {
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

    private BoardService boardService;
@Autowired
@Qualifier(value = "boardService")
    public void setBoardService(BoardService boardService) {
        this.boardService = boardService;
    }

    private Board_managerService board_managerService;
    @Autowired
    @Qualifier(value = "board_managerService")
    public void setBoard_managerService(Board_managerService board_managerService) {
        this.board_managerService = board_managerService;
    }

    private PostService postService;
@Autowired
@Qualifier(value = "postService")
    public void setPostService(PostService postService) {
        this.postService = postService;
    }
    //所有的帖子topic
    /**@RequestMapping(value = "/all",method = RequestMethod.GET)
    public String getAllTopices(HttpServletRequest request){
    List<Topic> topicList=topicService.getAllTopics();
    request.setAttribute("AllTopics",topicList);
    return "/topic/topicList";

    }
     **/


    //按 board_id查询topic&sticky_post并且分页
    @RequestMapping(value = "/{board_id}",method = RequestMethod.GET)
    public String selectBoard(@PathVariable("board_id") int board_id,Topic topic,
                              Model model,HttpServletRequest request){
        model.addAttribute("StickyList",this.topicService.getStickyByBoardId(board_id));
        Page pageModel=new Page();
        // 获取当前页
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
      //  String hql="from Topic t  where t.board_id=? and t.sticky_post=0 order by t.last_post desc";
        pageModel.setPageSize(13);
        pageModel.setTotalCount(topicService.getTopicByBoardId(board_id).size());
        pageModel.setNum(3);
        List<Topic> topicList=topicService.getTopicByPage(topic,board_id,pageModel.getPageSize(),pageModel.getPage());
      //  System.out.println("主题"+topicList);
        pageModel.setList(topicList);
//  model.addAttribute("topicList", this.topicService.getTopicByBoardId(board_id));

        request.setAttribute("topicList", topicList);
        request.setAttribute("count", topicList.size());
        request.setAttribute("pageModel", pageModel);
        request.setAttribute("page",pageModel.getPage());
        request.getSession(true).setAttribute("boardId",board_id);
        return "/topic/topicList";
    }


//去发表
@RequestMapping("/push")
    public String toPush(){
    return "/topic/pushTopic";
    }

    //发表时检测是否存在用户
    @RequestMapping("/checkTopicUser")
    public void checkTopicUser(HttpServletRequest request,HttpServletResponse response) throws Exception{
        Integer  currentUserId=(Integer)request.getSession().getAttribute("sessionuserId");
        PrintWriter pw=null;
        pw=response.getWriter();
        if(currentUserId==null){
            pw.print("{\"result\":\"0\"}");
        }else{
            pw.print("{\"result\":\"1\"}");
        }
    }


    //在板块发表新帖
    @RequestMapping(value = "/pushTopic",method = RequestMethod.POST)
    public String pushTopic(Topic topic,User user,HttpServletRequest request, RedirectAttributes redirectAttributes) throws  Exception{
        Integer boardId=(Integer) request.getSession().getAttribute("boardId");
        Integer  currentUserId=(Integer)request.getSession().getAttribute("sessionuserId");
        Board currentBoard=boardService.getBoardByID(boardId);
        String currentUserName=request.getSession().getAttribute("sessionusername").toString();
        User currentUser=userService.getByName(currentUserName);

        //增加一个帖子
        topicService.addTopic(topic, boardId, currentUserId, currentUserName);

        //版块主题数
        int topic_num=topicService.getAllTopicByBoardId(boardId).size();
        boardService.addTopicNumByBoardID(currentBoard,topic_num);

        // 用户积分增加
        userService.addPushTopicCredit(currentUser);

        //REST重定向
        redirectAttributes.addAttribute("board_id",topic.getBoard_id()).addFlashAttribute("message", "Account created!");
        if((Integer) request.getSession().getAttribute("sessionusertype")!=0){
            return "redirect:/topic/m/{board_id}";
        }
        else{
            return "redirect:/topic/{board_id}";
        }
    }

//返回
    @RequestMapping("/re")
    public String retohistory(RedirectAttributes redirectAttributes,Topic topic,HttpServletRequest request){
        redirectAttributes.addAttribute("board_id",request.getSession().getAttribute("boardId")).addFlashAttribute("message", "Account created!");
           return "redirect:/topic/{board_id}";
    }
    //返回版块页
    @RequestMapping("/reBoard")
    public String reBoard(){
       return "redirect:/";
    }

    /**
     *
     * ---------------------------------------管理主题帖----------------------------------------------
     *
     * */
    //按 board_id查询topic并分页
    @RequestMapping(value = "/m/{board_id}",method = RequestMethod.GET)
    public String MselectBoard(@PathVariable("board_id") int board_id,Topic topic,
                              Model model,HttpServletRequest request){
        model.addAttribute("StickyList",this.topicService.getStickyByBoardId(board_id));
        Page pageModel=new Page();
        // 获取当前页
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
        //  String hql="from Topic t  where t.board_id=? and t.sticky_post=0 order by t.last_post desc";
        pageModel.setPageSize(13);
        pageModel.setTotalCount(topicService.getTopicByBoardId(board_id).size());
        pageModel.setNum(3);

        List<Topic> topicList=topicService.getTopicByPage(topic,board_id,
                pageModel.getPageSize(),pageModel.getPage());
        //  System.out.println("主题"+topicList);
        pageModel.setList(topicList);
//  model.addAttribute("topicList", this.topicService.getTopicByBoardId(board_id));

        request.setAttribute("topicList", topicList);
        request.setAttribute("count", topicList.size());
        request.setAttribute("pageModel", pageModel);
        request.setAttribute("page",pageModel.getPage());

        request.getSession(true).setAttribute("boardId",board_id);
        return "/manager/topic/topicList";
    }


    //删除帖子
    @RequestMapping(value = "/m/deleteTopic")
    public String MdeleteTopic(Topic topic,int topic_id,RedirectAttributes redirectAttributes,HttpServletRequest request) throws  Exception{
        System.out.println("获取的topicid " + topic_id);
        topicService.deleteTopicById(topic_id);
        postService.deletePostByTopicID(topic_id);

        Integer boardId=(Integer) request.getSession().getAttribute("boardId");
        Board currentBoard=boardService.getBoardByID(boardId);
        //版块主题数
        int topic_num=topicService.getAllTopicByBoardId(boardId).size();
        boardService.addTopicNumByBoardID(currentBoard,topic_num);

        String currentBoardId=request.getSession().getAttribute("boardId").toString();
        redirectAttributes.addAttribute("board_id",currentBoardId).addFlashAttribute("message", "Account created!");
        return "redirect:/topic/m/{board_id}";
    }

    //置顶
    @RequestMapping("/m/stickyTopic")
    public String MstickyTopic(Topic topic,int topic_id ,RedirectAttributes redirectAttributes,HttpServletRequest request){
        topicService.stickyTopic(topic_id);
        String currentBoardId=request.getSession().getAttribute("boardId").toString();
        redirectAttributes.addAttribute("board_id",currentBoardId).addFlashAttribute("message", "Account created!");
        return "redirect:/topic/m/{board_id}";
    }

    //取消置顶
    @RequestMapping("/m/unstickyTopic")
    public String MunstickyTopic(int topic_id,RedirectAttributes redirectAttributes,HttpServletRequest request){
        topicService.unstickyTopic(topic_id);
        String currentBoardId=request.getSession().getAttribute("boardId").toString();
        redirectAttributes.addAttribute("board_id",currentBoardId).addFlashAttribute("message","Account created");
        return "redirect:/topic/m/{board_id}";
    }

    //返回
    @RequestMapping("/m/re")
    public String Mretohistory(RedirectAttributes redirectAttributes,Topic topic,HttpServletRequest request){
        redirectAttributes.addAttribute("board_id",request.getSession().getAttribute("boardId")).addFlashAttribute("message", "Account created!");
        return "redirect:/topic/m/{board_id}";
    }




}
