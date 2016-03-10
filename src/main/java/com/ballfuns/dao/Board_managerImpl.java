package com.ballfuns.dao;

import com.ballfuns.entity.Board;
import com.ballfuns.entity.Board_manager;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

/**
 * Created by Revol on 2016/1/17.
 */
@Repository
public class Board_managerImpl  implements Board_managerDao{
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    //通过板块id查询管理员id
    public Board_manager getManagerByBoardId(Board_manager board_manager, int board_id) {
        String hql="from Board_manager bm where bm.user_id=?";
        Query query=sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0, board_id);
        return (Board_manager) query.uniqueResult();
    }


//通过user_id 查询管理员
    public Board_manager getManagerByUserId(int user_id) {
        Session session=this.sessionFactory.getCurrentSession();
        Board_manager board_manager=(Board_manager) session.load(Board_manager.class,new Integer(user_id));
        return board_manager;
    }
}
