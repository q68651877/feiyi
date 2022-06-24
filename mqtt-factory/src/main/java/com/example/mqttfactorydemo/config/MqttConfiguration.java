package com.example.mqttfactorydemo.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author peiyuxiang
 * @date 2022/6/20
 */
@Data
@Component
@Configuration
public class MqttConfiguration {
    @Value("${spring.mqtt.broker}")
    private String host;

    @Value("${spring.mqtt.clientId}")
    private String clientId;

    @Value("${spring.mqtt.username}")
    private String username;

    @Value("${spring.mqtt.password}")
    private String password;

    @Value("${spring.mqtt.timeout}")
    private int timeout;

    @Value("${spring.mqtt.keepAlive}")
    private int keepAlive;

    @Value("${spring.mqtt.topics}")
    private String topics;

    @Value("${spring.mqtt.qos}")
    private int qos;


}
