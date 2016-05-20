package com.ballfuns.service;

import com.ballfuns.dao.PostDao;
import com.ballfuns.entity.Post;
import com.ballfuns.entity.Topic;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Revol on 2015/12/17.
 */
@Service
public class PostServiceImpl implements PostService {
    private PostDao postDao;

    public void setPostDao(PostDao postDao) {
        this.postDao = postDao;
    }
@Transactional
    public List<Post> getAllPost() {
        return postDao.getAllPost();
    }

@Transactional
    public List<Post> getPostByTopicId(int topic_id,Topic topic) {
        return postDao.getPostByTopicId(topic_id,topic);
    }

@Transactional
    public void addPost(Post post, int topic_id, int user_id, String user_name) {
    postDao.addPost(post,topic_id,user_id,user_name);
    }

    @Transactional
    public List<Post> getPostByTopicIdPage(int topic_id, Topic topic, int pageSize, int offset) {
        return postDao.getPostByTopicIdPage(topic_id,topic,pageSize,offset);
    }
    /**@Transactional
    public void deletePostByPostID(int post_id) {
    postDao.deletePostByPostID(post_id);
    }
     * */

@Transactional
    public void deletePostByTopicID(int topic_id) {
        postDao.deletePostByTopicID(topic_id);
    }
@Transactional
    public void deletePostByPostID( int post_id) {
        postDao.deletePostByPostID(post_id);
    }
}
