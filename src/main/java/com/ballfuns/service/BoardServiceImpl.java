package com.ballfuns.service;

import com.ballfuns.dao.BoardDao;
import com.ballfuns.entity.Board;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Revol on 2015/12/15.
 */
@Service
public class BoardServiceImpl implements BoardService {
    private BoardDao boardDao;

    public void setBoardDao(BoardDao boardDao) {
        this.boardDao = boardDao;
    }

    @Transactional
    public List<Board> getAllBoard() {
        return boardDao.getAllBoard();
    }
@Transactional
    public void setTopicNum(int topic_num) {
        boardDao.setTopicNum(topic_num);
    }
    @Transactional
    public void addBoard(Board board) {
        boardDao.addBoard(board);
    }
@Transactional
    public void addTopicNum(Board board) {
        boardDao.addTopicNum(board);
    }
@Transactional
    public Board getBoardByID(int board_id) {
        return boardDao.getBoardByID(board_id);
    }
@Transactional
    public void minusTopicNum(Board board) {
        boardDao.minusTopicNum(board);
    }

@Transactional
    public void addTopicNumByBoardID(Board board, int topicNum) {
        boardDao.addTopicNumByBoardID(board,topicNum);
    }
}
