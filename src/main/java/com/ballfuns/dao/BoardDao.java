package com.ballfuns.dao;

import com.ballfuns.entity.Board;

import java.util.List;

/**
 * Created by Revol on 2015/12/15.
 */
public interface BoardDao {
    public List<Board> getAllBoard();
    public void setTopicNum(int topic_num);
    public void addTopicNum(Board board);
    public void minusTopicNum(Board board);
    public void addBoard(Board board);
    public Board getBoardByID(int board_id);
    public void addTopicNumByBoardID(Board board,int topicNum);

}
