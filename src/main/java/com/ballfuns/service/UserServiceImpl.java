package com.ballfuns.service;

import com.ballfuns.dao.UserDao;
import com.ballfuns.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Revol on 2015/12/18.
 */
@Service
public class UserServiceImpl implements UserService {
    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Transactional
    public void addUser(User user) {
        userDao.addUser(user);
    }
@Transactional
    public User getByAccount(String user_account) {
        return userDao.getByAccount(user_account);
    }
@Transactional
    public User getByName(String user_name) {
        return userDao.getByName(user_name);
    }
@Transactional
    public void addLoginCredit(User user) {
        userDao.addLoginCredit(user);
    }
@Transactional
    public void updatePwd(User user, String password) {
    userDao.updatePwd(user,password);
    }
@Transactional
    public List<User> getAllUser(String hql,User user, int pageSize, int offset) {
        return userDao.getAllUser(hql,user,pageSize,offset);
    }


    @Transactional
    public List<User> getAllUserList(User user) {
        return userDao.getAllUserList(user);
    }



@Transactional
    public void deleteUserById(int user_id) {
        userDao.deleteUserById(user_id);
    }
@Transactional
    public void lockUserById(int user_id) {
    userDao.lockUserById(user_id);
    }
@Transactional
    public void unlockUserById(int user_id) {
        userDao.unlockUserById(user_id);
    }
@Transactional
    public void setManager(int user_id) {
        userDao.setManager(user_id);
    }
@Transactional
    public void offManager(int user_id) {
userDao.offManager(user_id);
    }
    @Transactional
    public void addPushTopicCredit(User user) {
        userDao.addPushTopicCredit(user);
    }
    @Transactional
    public void addPushPostCredit(User user) {
userDao.addPushPostCredit(user);
    }
}
