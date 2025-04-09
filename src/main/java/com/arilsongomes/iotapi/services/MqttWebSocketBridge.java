package com.arilsongomes.iotapi.services;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.eclipse.paho.client.mqttv3.*;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MqttWebSocketBridge implements MqttCallback {

    private final IMqttAsyncClient mqttClient;
    private final SimpMessagingTemplate websocketTemplate;
    //private final JwtTokenProvider jwtTokenProvider;

    @PostConstruct
    public void init() throws MqttException {
        mqttClient.setCallback(this);
        mqttClient.subscribe("iot/#", 1);
    }

    @Override
    public void connectionLost(Throwable throwable) {

    }

    @Override
    public void messageArrived(String topic, MqttMessage message) {

        String[] parts = topic.split("/");
        if(parts.length >= 4) {
            String userId = parts[1];
            String deviceId = parts[2];
            String subtopic = parts[3];

            String destination = String.format(
                    "/topic/devices/%s/%s/%s",
                    userId, deviceId, subtopic
            );
            System.out.println(destination);

            websocketTemplate.convertAndSend(destination, new String(message.getPayload()));
        }
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

    }

    // Outros métodos do MqttCallback (connectComplete, deliveryComplete, connectionLost)
}