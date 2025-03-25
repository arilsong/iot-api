package com.arilsongomes.iotapi.controllers;

import com.arilsongomes.iotapi.services.MqttMessage.MqttMessageService;
import lombok.RequiredArgsConstructor;
import org.aspectj.bridge.Message;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/api/message")
@RequiredArgsConstructor
public class MqttMessageController {
    private final MqttMessageService messageService;

    @PostMapping("publish/{topic}")
    public void publishMessage(@PathVariable String topic){
        messageService.publishMessage(topic);
    }
}
