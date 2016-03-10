package com.ballfuns.dao;

import com.ballfuns.entity.Topic;

import java.util.List;

/**
 * Created by Revol on 2015/12/16.
 */
public interface TopicDao {
    public List<Topic> getAllTopics();
    public List<Topic> getTopicByBoardId(int board_id);
    public Topic getTopicById(int topic_id);
    public Topic getTopicByUserName(String user_name);
    public void addTopic(Topic topic,int board_id,int user_id,String user_name);
    public void addTopicReply(Topic topic,int reply);
    public void addTopiclastTime(Topic topic);
    public  void deleteTopicById(int topic_id);
    public List<Topic> getStickyByBoardId(int board_id);
    public List<Topic> getTopicByPage(Topic topic,int board_id,int pageSize,int offset);
    public void stickyTopic(int topic_id);
    public void unstickyTopic(int topic_id);
    public void setDigest(int topic_id);
    public void offDigest(int topic_id);
}
