package com.ballfuns.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by Revol on 2015/12/16.
 */
@Entity
@Table(name = "login_log")
public class Login_log {
    @Id
    @Column(name = "login_id")
    private int login_id;
    private int user_id;
    private String ip;
    private Date login_datetime;

    public int getLogin_id() {
        return login_id;
    }

    public void setLogin_id(int login_id) {
        this.login_id = login_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Date getLogin_datetime() {
        return login_datetime;
    }

    public void setLogin_datetime(Date login_datetime) {
        this.login_datetime = login_datetime;
    }
}
