package com.ballfuns.service;

import com.ballfuns.entity.Post;
import com.ballfuns.entity.Topic;

import java.util.List;

/**
 * Created by Revol on 2015/12/17.
 */
public interface PostService {
    public List<Post> getAllPost();
    public List<Post> getPostByTopicId(int topic_id,Topic topic);
    public void addPost(Post post,int topic_id,int user_id,String user_name);
    public List<Post> getPostByTopicIdPage(int topic_id,Topic topic,int pageSize,int offset);
    //public void deletePostByPostID(int post_id);
    public void deletePostByPostID(int post_id);

    public void deletePostByTopicID(int topic_id);

}
