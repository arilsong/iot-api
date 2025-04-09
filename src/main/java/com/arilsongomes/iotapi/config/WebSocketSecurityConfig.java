package com.arilsongomes.iotapi.config;

import com.arilsongomes.iotapi.exceptions.UnauthorizedException;
import com.arilsongomes.iotapi.services.webSocket.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import java.nio.charset.StandardCharsets;
import java.nio.file.AccessDeniedException;
import java.util.Map;

@Configuration
@EnableWebSocketMessageBroker

public class WebSocketSecurityConfig implements WebSocketMessageBrokerConfigurer {

    @Autowired
    private SubscriptionService subscriptionService;

    @Autowired
    private ApplicationEventPublisher eventPublisher;


    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(new ChannelInterceptor() {

            public Message<?> preSend(Message<?> message, MessageChannel channel) {
                StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
                String sessionId = accessor.getSessionId();


                if (StompCommand.SUBSCRIBE.equals(accessor.getCommand())) {
                        Map<String, Object> sessionAttributes = accessor.getSessionAttributes();
                        if (sessionAttributes == null || !sessionAttributes.containsKey("userId")) {
                            throw new UnauthorizedException("Subscription denied");
                        }

                        Long userId = (Long) sessionAttributes.get("userId");
                        String topic = accessor.getDestination();

                        if (!subscriptionService.isValidSubscription(userId, topic)) {
                            createErrorMessage(accessor, "Subscription denied");
                            throw new UnauthorizedException("Subscription denied");
                        }


                }
                return message;
            }

            private Message<?> createErrorMessage(StompHeaderAccessor accessor, String errorMessage) {
                StompHeaderAccessor errorHeaderAccessor = StompHeaderAccessor.create(StompCommand.ERROR);
                errorHeaderAccessor.setSessionId(accessor.getSessionId());
                errorHeaderAccessor.setMessage(errorMessage);
                errorHeaderAccessor.setLeaveMutable(true);

                return MessageBuilder.createMessage(
                        errorMessage.getBytes(StandardCharsets.UTF_8),
                        errorHeaderAccessor.getMessageHeaders());
            }


        });
    }



}
