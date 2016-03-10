package com.ballfuns.dao;

import com.ballfuns.entity.Board;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Revol on 2015/12/15.
 */
@Repository
public class BoardDaoImpl implements BoardDao {
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Board> getAllBoard() {
        String hql="from Board";
        Query query=sessionFactory.getCurrentSession().createQuery(hql);
        return query.list();
    }

    public void setTopicNum(int topic_num) {
        Session session=sessionFactory.getCurrentSession();
        Board board=(Board) session.load(Board.class,new Integer(topic_num));
        if(board!=null){
            board.setTopic_num(topic_num);
            session.save(board);
        }
    }

    public void addBoard(Board board) {
        sessionFactory.getCurrentSession().save(board);
    }

    public void addTopicNum(Board board) {
        int topicNum=board.getTopic_num()+1;
        board.setTopic_num(topicNum);
        sessionFactory.getCurrentSession().save(board);
    }

    public Board getBoardByID(int board_id) {
        String hql="from Board b where b.board_id=?";
        Query query=sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0,board_id);
        return (Board) query.uniqueResult();
    }
}
