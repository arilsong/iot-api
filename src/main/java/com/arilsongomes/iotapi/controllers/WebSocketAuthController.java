package com.arilsongomes.iotapi.controllers;

import com.arilsongomes.iotapi.entity.User;
import com.arilsongomes.iotapi.repositories.UserRepository;
import com.arilsongomes.iotapi.security.GetEmailFomUserAuhtenticaed;
import com.arilsongomes.iotapi.services.webSocket.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

import java.util.Map;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class WebSocketAuthController {


    private final SubscriptionService subscriptionService;
    private final GetEmailFomUserAuhtenticaed getEmailFomUserAuhtenticaed;
    private final UserRepository userRepository;

    @MessageMapping("/subscribe")
    @SendToUser("/queue/errors")
    public String handleSubscribe(@Payload String topic,
                                  SimpMessageHeaderAccessor headerAccessor) {

        //Long userId = headerAccessor.getUser().getName();

        if (!subscriptionService.isValidSubscription(1L, topic)) {
            return "Acess denied to topic" + topic;
        }

        return null;
    }
}