package com.ballfuns.service;

import com.ballfuns.dao.TopicDao;
import com.ballfuns.entity.Topic;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Revol on 2015/12/16.
 */
@Service
public class TopicServiceImpl implements TopicService {
    private TopicDao topicDao;

    public void setTopicDao(TopicDao topicDao) {
        this.topicDao = topicDao;
    }

    @Transactional
    public List<Topic> getAllTopics() {
        return topicDao.getAllTopics();
    }
@Transactional
    public List<Topic> getAllTopicByBoardId(int board_id) {
        return topicDao.getAllTopicByBoardId(board_id);
    }

    @Transactional
    public List<Topic> getTopicByBoardId(int board_id) {
        return topicDao.getTopicByBoardId(board_id);
    }
@Transactional
    public Topic getTopicById(int topic_id) {
        return topicDao.getTopicById(topic_id);
    }
@Transactional
    public Topic getTopicByUserName(String user_name) {
        return topicDao.getTopicByUserName(user_name);
    }

    @Transactional
    public void addTopic(Topic topic, int board_id, int user_id, String user_name) {
        topicDao.addTopic(topic, board_id, user_id, user_name);
    }

@Transactional
    public void addTopicReply(Topic topic,int reply) {
        topicDao.addTopicReply(topic,reply);
    }
@Transactional
    public void addTopiclastTime(Topic topic) {
        topicDao.addTopiclastTime(topic);
    }

    @Transactional
    public void deleteTopicById(int topic_id) {
        topicDao.deleteTopicById(topic_id);
    }
@Transactional
    public List<Topic> getStickyByBoardId(int board_id) {
        return topicDao.getStickyByBoardId(board_id);
    }

    @Transactional
    public List<Topic> getTopicByPage(Topic topic, int board_id, int pageSize, int offset) {
        return topicDao.getTopicByPage(topic,board_id,pageSize,offset);
    }

    @Transactional
    public void stickyTopic( int topic_id) {
        topicDao.stickyTopic(topic_id);
    }

@Transactional
    public void unstickyTopic(int topic_id) {
    topicDao.unstickyTopic(topic_id);
    }

    @Transactional
    public void setDigest(int topic_id) {
    topicDao.setDigest(topic_id);
    }

    @Transactional
    public void offDigest(int topic_id) {
topicDao.offDigest(topic_id);
    }
}
