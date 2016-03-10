package com.ballfuns.dao;

import com.ballfuns.entity.Post;
import com.ballfuns.entity.Topic;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Revol on 2015/12/17.
 */
@Repository
public class PostDaoImpl implements PostDao {
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Post> getAllPost() {
        String hql="from  Post";
        Query query=sessionFactory.getCurrentSession().createQuery(hql);
        return query.list();
    }

    public List<Post> getPostByTopicId(int topic_id,Topic topic) {
        String hql="from Post p where p.topic_id=?";
        Query query=sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0,topic_id);
        System.out.println("get post successfully*********** topic id=" + topic_id);
      //  int currentview=topic.getTopic_views()+1;
        //topic.setTopic_views(currentview);
        return query.list();
    }

    //查询对应的回复，并分页
    public List<Post> getPostByTopicIdPage(int topic_id,Topic topic, int pageSize, int offset) {
        String hql="from Post p where p.topic_id=?";
        Query query=sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0,topic_id);
        System.out.println("get post successfully*********** topic id=" + topic_id);
        int currentview=topic.getTopic_views()+1;
        topic.setTopic_views(currentview);
        query.setFirstResult((offset-1)*pageSize);
        query.setMaxResults(pageSize).list();
        return query.list();
    }

    public void addPost(Post post, int topic_id, int user_id, String user_name) {
        post.setUser_id(user_id);
        post.setUser_name(user_name);
        post.setTopic_id(topic_id);

        sessionFactory.getCurrentSession().save(post);
    }

    public void deletePostByPostID(int post_id) {
        Session session=sessionFactory.getCurrentSession();
        Post post=(Post) session.load(Post.class,new Integer(post_id));
        if(post!=null){
            session.delete(post);
            System.out.println("DELETE SUCCESSFULLY***");
        }
    }
}
