package com.ballfuns.service;

import com.ballfuns.entity.Board;

import java.util.List;

/**
 * Created by Revol on 2015/12/15.
 */
public interface BoardService {
    public List<Board> getAllBoard();
    public void setTopicNum(int topic_num);
    public void addBoard(Board board);
    public void addTopicNum(Board board);
    public Board getBoardByID(int board_id);

}
