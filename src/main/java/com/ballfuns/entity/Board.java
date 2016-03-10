package com.ballfuns.entity;

import javax.persistence.*;

/**
 * Created by Revol on 2015/12/15.
 */
@Entity
@Table(name = "board")
public class Board {
    @Id
    @Column(name = "board_id")
    private int board_id;

    @Column(name ="board_name")
    private String board_name;

    @Column(name = "board_des")
    private String board_des;

    @Column(name = "topic_num")
    private int topic_num;

    public int getBoard_id() {
        return board_id;
    }

    public void setBoard_id(int board_id) {
        this.board_id = board_id;
    }

    public String getBoard_name() {
        return board_name;
    }

    public void setBoard_name(String board_name) {
        this.board_name = board_name;
    }

    public String getBoard_des() {
        return board_des;
    }

    public void setBoard_des(String board_des) {
        this.board_des = board_des;
    }

    public int getTopic_num() {
        return topic_num;
    }

    public void setTopic_num(int topic_num) {
        this.topic_num = topic_num;
    }
}
