package com.ballfuns.service;

import com.ballfuns.dao.Board_managerDao;
import com.ballfuns.entity.Board_manager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Revol on 2016/1/17.
 */
@Service
public class Board_managerServiceImpl implements Board_managerService {
    private Board_managerDao board_managerDao;

    public void setBoard_managerDao(Board_managerDao board_managerDao) {
        this.board_managerDao = board_managerDao;
    }

@Transactional
    public Board_manager getManagerByBoardId(Board_manager board_manager, int board_id) {
        return board_managerDao.getManagerByBoardId(board_manager,board_id);
    }
@Transactional
    public Board_manager getManagerByUserId(int user_id) {
        return board_managerDao.getManagerByUserId(user_id);
    }
}
