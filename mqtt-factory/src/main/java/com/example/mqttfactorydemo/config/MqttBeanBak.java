package com.example.mqttfactorydemo.config;

import com.example.mqttfactorydemo.mqtt.MqttConnection;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author peiyuxiang
 * @date 2022/6/20
 */
@Component
public class MqttBeanBak {
    @Resource
    private MqttConfiguration mqttConfiguration;
    @Bean("mqttPushClient")
    public MqttConnection getMqttPushClient() {
        return new MqttConnection();
    }

}
