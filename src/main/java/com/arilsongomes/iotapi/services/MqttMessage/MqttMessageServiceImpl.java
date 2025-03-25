package com.arilsongomes.iotapi.services.MqttMessage;

import com.arilsongomes.iotapi.dto.MqttMessage.MqttMessageResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MqttMessageServiceImpl implements MqttMessageService{
    @Override
    public void publishMessage(String topic) {

    }

    @Override
    public List<MqttMessageResponseDto> getAllMessage(String email) {
        return List.of();
    }

    @Override
    public void removeMessage(Long idMessage) {

    }
}
