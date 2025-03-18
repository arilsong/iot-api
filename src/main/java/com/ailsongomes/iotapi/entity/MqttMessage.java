package com.ailsongomes.iotapi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "mqtt_messages")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MqttMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mac_address", nullable = false)
    private Device device;

    @Column(nullable = false)
    private String topic;

    @Column(nullable = false)
    private String payload;

    @Column(nullable = false)
    private LocalDateTime timestamp = LocalDateTime.now();

    @Column(nullable = false)
    private int qos;

    @Column(nullable = false)
    private boolean retained = false;
}
