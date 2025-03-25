package com.arilsongomes.iotapi.services.topic;

import com.arilsongomes.iotapi.dto.topic.TopicRequestDto;
import com.arilsongomes.iotapi.dto.topic.TopicResponseDto;

import java.util.List;

public interface TopicService {
    public void registerTopic(TopicRequestDto topicRequest, String email, String deviceId);
    public void updateTopic(Long topicId, TopicRequestDto requestDto, String email);
    public List<TopicResponseDto> getAllTopic(String email);
    public void removeTopic(Long topicId, String email);
}
