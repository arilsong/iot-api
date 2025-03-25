package com.arilsongomes.iotapi.dto.topic;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TopicRequestDto(@NotBlank String topicPattern, @NotNull int qos){
}
