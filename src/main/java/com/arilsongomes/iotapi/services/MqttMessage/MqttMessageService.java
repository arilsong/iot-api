package com.arilsongomes.iotapi.services.MqttMessage;

import com.arilsongomes.iotapi.dto.MqttMessage.MqttMessageResponseDto;
import org.aspectj.bridge.Message;

import java.util.List;

public interface MqttMessageService {
    public void publishMessage(String topic);
    public List<MqttMessageResponseDto> getAllMessage(String email);
    public void removeMessage(Long idMessage);
}
