package com.arilsongomes.iotapi.services.webSocket;

import com.arilsongomes.iotapi.repositories.TopicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubscriptionService {
    private final TopicRepository topicRepository;

    public boolean isValidSubscription(Long userId, String requestedTopic) {
        // 1. Verifica se o tópico segue o padrão permitido
        if (!requestedTopic.matches("^/topic/devices/" + userId + "/[a-zA-Z0-9-_]+(/[a-zA-Z0-9-_]+)*$")) {
            return false;
        }

        // 2. Verifica no banco de dados se o usuário tem acesso
        return topicRepository.existsByUserIdAndTopicPattern(userId, requestedTopic);
    }
}