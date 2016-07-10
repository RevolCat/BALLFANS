package com.ballfuns.dao;

import com.ballfuns.entity.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Revol on 2015/12/18.
 */
@Repository
public class UserDaoImpl implements UserDao {
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void addUser(User user) {
        user.setCredit(10);
        sessionFactory.getCurrentSession().save(user);
    }
//检测账号是否存在
public User getByAccount(String user_account){
        String hql="from User u where u.user_account=?";
        Query query=sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0,user_account);
        return (User) query.uniqueResult();
    }
//检测用户名是否重复
    public User getByName(String user_name) {
        String hql="from User u where u.user_name=?";
        Query query=sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0, user_name);
        return (User) query.uniqueResult();
    }

    //登录加积分
    public void addLoginCredit(User user) {
        int currentcredit=user.getCredit()+1;
        user.setCredit(currentcredit);
        sessionFactory.getCurrentSession().save(user);
    }

    //发帖加积分

    public void addPushTopicCredit(User user) {
        int currentcredit=user.getCredit()+2;
        user.setCredit(currentcredit);
        sessionFactory.getCurrentSession().save(user);
    }
    //回复加积分
    public void addPushPostCredit(User user) {
        int currentcredit=user.getCredit()+1;
        user.setCredit(currentcredit);
        sessionFactory.getCurrentSession().save(user);
    }



    public void updatePwd(User user,String password) {
        user.setPassword(password);
        sessionFactory.getCurrentSession().save(user);
    }

    public List<User> getAllUser(String hql,User user,int pageSize,int offset) {
        //String hql="from User";
        Query query=sessionFactory.getCurrentSession().createQuery(hql);
        query.setFirstResult((offset-1)*pageSize);
        query.setMaxResults(pageSize).list();
        return query.list();
    }

    public List<User> getAllUserList(User user) {
       String hql="from User";
        Query query=sessionFactory.getCurrentSession().createQuery(hql);
        return query.list();
    }

    public void deleteUserById(int user_id) {
        Session session=this.sessionFactory.getCurrentSession();
        User user=(User) session.load(User.class,new Integer(user_id));
        if(user!=null){
            session.delete(user);
            //System.out.println("user delete successfully+++++"+user);
        }
    }

    public void lockUserById(int user_id) {
        Session session=this.sessionFactory.getCurrentSession();
        User user=(User) session.load(User.class,new Integer(user_id));
        if(user!=null){
            user.setLocked(1);
            session.save(user);
         //   System.out.println("locked successfully+++");
        }
    }

    public void unlockUserById(int user_id) {
        Session session=this.sessionFactory.getCurrentSession();
        User user=(User) session.load(User.class,new Integer(user_id));
        if(user!=null){
            user.setLocked(0);
            session.save(user);
           // System.out.println("locked successfully+++");
        }
    }

    public void setManager(int user_id) {
        Session session=this.sessionFactory.getCurrentSession();
        User user=(User) session.load(User.class,new Integer(user_id));
        if(user!=null){
            user.setUser_type(2);
            session.save(user);

        }
    }

    public void offManager(int user_id) {
        Session session=this.sessionFactory.getCurrentSession();
        User user=(User) session.load(User.class,new Integer(user_id));
        if(user!=null){
            user.setUser_type(0);
            session.save(user);
        }
    }
}
