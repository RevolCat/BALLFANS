package com.ballfuns.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Revol on 2015/12/16.
 */
@Entity
@Table(name = "board_manager")
public class Board_manager {
    @Id
    @Column(name = "board_id")
    private int board_id;

    @Column(name = "user_id")
    private int user_id;

    public int getBoard_id() {
        return board_id;
    }

    public void setBoard_id(int board_id) {
        this.board_id = board_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
