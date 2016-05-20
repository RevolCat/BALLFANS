package com.ballfuns.controller;

import com.ballfuns.entity.Board;
import com.ballfuns.service.BoardService;
import com.ballfuns.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Revol on 2015/12/15.
 */
@Controller
@RequestMapping("/")
public class boardController {
    private BoardService boardService;
    @Autowired
    @Qualifier(value = "boardService")
    public void setBoardService(BoardService boardService) {
        this.boardService = boardService;
    }

    private TopicService topicService;
    @Autowired
    @Qualifier(value = "topicService")
    public void setTopicService(TopicService topicService) {
        this.topicService = topicService;
    }

    /**
     * @RequestMapping("/")
    public String HomePage(){
    return "home";
    }
     * */


    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String getAllBoard(HttpServletRequest request){
        List<Board> boardList=boardService.getAllBoard();
        request.setAttribute("AllBoard",boardList);


        return   "home";

    }
    @RequestMapping("/boardShow")
    public String topiclist(){
        return "boardShow";
    }


    /**
     *
     * ---------------------------------------管理----------------------------------------------
     *
     * */

    //管理员界面
    @RequestMapping("/managerhome")
    public String toManager(){
        return "/manager/managerhome";
    }

    @RequestMapping(value = "/managerhome",method = RequestMethod.GET)
    public String getAllBoardM(HttpServletRequest request){
        List<Board> boardList=boardService.getAllBoard();
        request.setAttribute("AllBoard",boardList);
        return "/manager/managerhome";
    }


    //root界面
    @RequestMapping("/roothome")
    public String toRoot(){
        return "/manager/roothome";
    }
    @RequestMapping(value = "/roothome",method = RequestMethod.GET)
    public String getAllBoardR(HttpServletRequest request){
        List<Board> boardList=boardService.getAllBoard();
        request.setAttribute("AllBoard",boardList);
        return "/manager/roothome";
    }

    @RequestMapping("/m/toAddBoard")
    public String toAddBoard(){
        return "/manager/addBoard";
    }


    @RequestMapping("/m/addboard")
    public String AddBoard(Board board){
        boardService.addBoard(board);
        return "redirect:/";
    }

}
