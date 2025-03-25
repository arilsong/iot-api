package com.arilsongomes.iotapi.dto.topic;


import com.arilsongomes.iotapi.entity.Device;

public record TopicResponseDto(String topicPattern, int qos) {
}
