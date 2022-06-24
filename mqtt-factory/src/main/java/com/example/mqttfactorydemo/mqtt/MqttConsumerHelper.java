package com.example.mqttfactorydemo.mqtt;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.stereotype.Service;

/**
 * MQTT 消费者帮助类
 * @author peiyuxiang
 * @date 2022/6/20
 */
@Slf4j
@Service(value = "mqttConsumerHelper")
public class MqttConsumerHelper {

    /**
     * 订阅多个主题
     *
     * @param topic 主题
     */
    public void subscribe(String[] topic) {
        try {
            MqttConnection.getClient().unsubscribe(topic);
            MqttConnection.getClient().subscribe(topic);
        } catch (MqttException e) {
            log.error("=======MqttConsumerHelper======= MQTT消费者主题订阅失败", e);
        }
    }

    /**
     * 清空主题
     *
     * @param topic 主题
     */
    public void cleanTopic(String topic) {
        try {
            MqttConnection.getClient().unsubscribe(topic);
        } catch (MqttException e) {
            log.error(e.getMessage());
            log.error("=======MqttConsumerHelper======= MQTT消费者主题清空失败", e);
        }
    }


}
