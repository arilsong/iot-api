package com.arilsongomes.iotapi.repositories;

import com.arilsongomes.iotapi.entity.MqttMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MqttMessageRepository extends JpaRepository<MqttMessage, Long> {
}
