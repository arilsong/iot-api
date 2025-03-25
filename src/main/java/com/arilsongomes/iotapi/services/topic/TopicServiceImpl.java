package com.arilsongomes.iotapi.services.topic;

import com.arilsongomes.iotapi.dto.topic.TopicRequestDto;
import com.arilsongomes.iotapi.dto.topic.TopicResponseDto;
import com.arilsongomes.iotapi.entity.Device;
import com.arilsongomes.iotapi.entity.Topic;
import com.arilsongomes.iotapi.entity.User;
import com.arilsongomes.iotapi.exceptions.UnauthorizedException;
import com.arilsongomes.iotapi.repositories.DeviceRepository;
import com.arilsongomes.iotapi.repositories.TopicRepository;
import com.arilsongomes.iotapi.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TopicServiceImpl implements TopicService{
    private final UserRepository userRepository;
    private final DeviceRepository deviceRepository;
    private final TopicRepository topicRepository;

    @Override
    public void registerTopic(TopicRequestDto topicRequest, String email, String deviceId) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isEmpty()) {
            throw new UnauthorizedException("User not found");
        }

        Device device = deviceRepository.findById(deviceId)
                .orElseThrow(() -> new RuntimeException("Device not found"));


        Topic topic = new Topic();
        topic.setQos(topicRequest.qos());
        topic.setTopicPattern(generateMqttTopicPath(topicRequest.topicPattern(), deviceId, email));
        topic.setUser(user.get());
        topic.setDevice(device);

        topicRepository.save(topic);
    }

    @Override
    @Transactional
    public void updateTopic(Long topicId, TopicRequestDto requestDto, String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UnauthorizedException("User not found"));

        Topic topic = topicRepository.findById(topicId)
                .orElseThrow(() -> new RuntimeException("Topic not found"));



        topic.setTopicPattern(generateMqttTopicPath(requestDto.topicPattern(), topic.getDevice().getId(), email));
        topicRepository.save(topic);
    }

    @Override
    public List<TopicResponseDto> getAllTopic(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UnauthorizedException("User not found"));

        return topicRepository.findByUser(user).stream()
                .map(topic -> new TopicResponseDto(
                        topic.getTopicPattern(),
                        topic.getQos()
                )).toList();
    }

    @Override
    @Transactional
    public void removeTopic(Long topicId, String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UnauthorizedException("User not found"));

        Topic topic = topicRepository.findById(topicId)
                .orElseThrow(() -> new RuntimeException("Topic not found"));

        topicRepository.delete(topic);
    }

    private String generateMqttTopicPath(String topicName, String deviceId, String email) {
        return String.format("%s/%s/%s",
                email,
                deviceId,
                topicName
                );
    }


}
