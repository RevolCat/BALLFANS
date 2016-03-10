package com.ballfuns.dao;

import com.ballfuns.entity.User;

import java.util.List;

/**
 * Created by Revol on 2015/12/18.
 */
public interface UserDao {
    public void addUser(User user);
    public User getByAccount(String user_account);
    public User getByName(String user_name);
    public void addLoginCredit(User user);
  public void updatePwd(User user,String password);
    public List<User> getAllUser(String hql,User user,int pageSize,int offset);
    public List<User> getAllUserList(User user);
    public  void deleteUserById(int user_id);
    public  void lockUserById(int user_id);
    public  void unlockUserById(int user_id);

    public void setManager(int user_id);
    public void offManager(int user_id);
}
