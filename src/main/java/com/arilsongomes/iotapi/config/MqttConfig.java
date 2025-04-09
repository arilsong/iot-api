package com.arilsongomes.iotapi.config;

import lombok.extern.java.Log;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MqttDefaultFilePersistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;

@Configuration
public class MqttConfig {

    private static final Logger log = LoggerFactory.getLogger(MqttConfig.class);
    @Value("${mqtt.broker.url}")
    private String broker;

    @Value("${mqtt.client.id}")
    private String clientId;

    @Value("${mqtt.client.username}")
    private String username;

    @Value("${mqtt.client.password}")
    private String password;

    @Bean
    public IMqttAsyncClient mqttAsyncClient() throws MqttException {
        String persistenceDirectory = "./mqtt-persistence/";

        MqttDefaultFilePersistence persistence = new MqttDefaultFilePersistence(persistenceDirectory);

        IMqttAsyncClient client = new MqttAsyncClient(broker, clientId + System.currentTimeMillis(), persistence);
        MqttConnectOptions options = new MqttConnectOptions();
        options.setCleanSession(false);
        options.setUserName(username);
        options.setPassword(password.toCharArray());
        options.setAutomaticReconnect(true);
        options.setConnectionTimeout(10);
        options.setKeepAliveInterval(60);

        client.connect(options).waitForCompletion();
        if (!client.isConnected()) {
            log.warn("Mqtt client not connected");
        }
         log.info("Broker connected!");
        return client;
    }
}