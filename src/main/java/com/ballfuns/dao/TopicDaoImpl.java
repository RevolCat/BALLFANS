package com.ballfuns.dao;

import com.ballfuns.entity.Post;
import com.ballfuns.entity.Topic;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by Revol on 2015/12/16.
 */
@Repository
public class TopicDaoImpl implements TopicDao {
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    //��ȡ���е�����
    public List<Topic> getAllTopics() {
        String hql="from Topic";
        Query query=sessionFactory.getCurrentSession().createQuery(hql);
        return query.list();
    }
    //���ݰ��id��ѯ�ð�������
    public List<Topic> getAllTopicByBoardId(int board_id) {
        String hql1="from Topic t  where t.board_id=? ";
        Query query1=sessionFactory.getCurrentSession().createQuery(hql1);

        // String hql="from Topic t where t.board_id=?";
        //Query query=sessionFactory.getCurrentSession().createQuery(hql);
        //��������0��������board_id
        query1.setParameter(0, board_id);
        System.out.println("board id=" + board_id);
        return query1.list();
    }
    //���ݰ��id��ѯ�ð�������
    public List<Topic> getTopicByBoardId(int board_id) {
        String hql1="from Topic t  where t.board_id=? and t.sticky_post=0 order by t.last_post desc";
        Query query1=sessionFactory.getCurrentSession().createQuery(hql1);

        // String hql="from Topic t where t.board_id=?";
        //Query query=sessionFactory.getCurrentSession().createQuery(hql);
        //��������0��������board_id
        query1.setParameter(0, board_id);
        System.out.println("board id=" + board_id);
        return query1.list();
            }

    public Topic getTopicById(int topic_id) {
        Session session=this.sessionFactory.getCurrentSession();
        Topic topic=(Topic) session.load(Topic.class,new Integer(topic_id));
        System.out.println("Topic loaded successfully,topic  details="+topic);
        return topic;
    }



    public Topic getTopicByUserName(String user_name) {
        Session session=this.sessionFactory.getCurrentSession();
        Topic topic=(Topic) session.load(Topic.class,new String(user_name));
        System.out.println("User's topics loaded successfully  ?=" + topic);
        return topic;
    }

    public void addTopic(Topic topic, int board_id, int user_id, String user_name) {
        topic.setBoard_id(board_id);
        topic.setUser_id(user_id);
        topic.setUser_name(user_name);
        sessionFactory.getCurrentSession().save(topic);
    }

    public void addTopicReply(Topic topic,int reply) {
        topic.setTopic_replies(reply);
        sessionFactory.getCurrentSession().save(topic);
    }


    public void addTopiclastTime(Topic topic) {
        Date date=new Date();
        topic.setLast_post(date);
        sessionFactory.getCurrentSession().save(topic);
    }

    public void deleteTopicById(int topic_id) {
        Session session=sessionFactory.getCurrentSession();
        Topic topic=(Topic) session.load(Topic.class,new Integer(topic_id));

        if(topic!=null){
            session.delete(topic);
            System.out.println("ɾ���ɹ�"+topic);
        }
    }

    //��ѯ�ö���������
    public List<Topic> getStickyByBoardId(int board_id) {
        String hql="from Topic t  where t.board_id=? and t.sticky_post=1 order by t.last_post desc";
        Query query=sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0, board_id);
        //System.out.println("the sticky   ");
        return query.list();
    }

    public List<Topic> getTopicByPage(Topic topic,int board_id, int pageSize, int offset) {
        String hql="from Topic t  where t.board_id=? and t.sticky_post=0 order by t.last_post desc";
        Query query=sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0,board_id);
        //���ĸ�λ�ÿ�ʼ��ѯ
        query.setFirstResult((offset-1)*pageSize);
        //ÿҳ�ļ�¼��
        query.setMaxResults(pageSize).list();
        return query.list();
    }

    public void stickyTopic(int topic_id) {
        Session session=sessionFactory.getCurrentSession();
        Topic topic=(Topic ) session.load(Topic.class,new Integer(topic_id));
        if(topic!=null){
            topic.setSticky_post(1);
            session.save(topic);
        }
    }

    public void unstickyTopic(int topic_id) {
        Session session=sessionFactory.getCurrentSession();
        Topic topic=(Topic) session.load(Topic.class,new Integer(topic_id));
        if(topic!=null){
            topic.setSticky_post(0);
            session.save(topic);
        }
    }

    public void setDigest(int topic_id) {
        Session session=sessionFactory.getCurrentSession();
        Topic topic=(Topic) session.load(Topic.class,new Integer(topic_id));
        if(topic!=null){
            topic.setDigest(1);
            session.save(topic);
        }
    }

    public void offDigest(int topic_id) {
        Session session=sessionFactory.getCurrentSession();
        Topic topic=(Topic) session.load(Topic.class,new Integer(topic_id));
        if(topic!=null){
            topic.setDigest(0);
            session.save(topic);
        }
    }
}
