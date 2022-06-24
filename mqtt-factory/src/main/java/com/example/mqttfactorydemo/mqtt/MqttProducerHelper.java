package com.example.mqttfactorydemo.mqtt;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

/**
 * MQTT生产者帮助类
 *
 * @author peiyuxiang
 * @date 2022/6/20
 */
@Service(value = "mqttSender")
@Slf4j
public class MqttProducerHelper {


    @Async
    public void send(String queueName, String msg) {
        log.info("=======MqttProducerHelper======= 发送主题" + queueName);
        publish(2, queueName, msg);
    }


    /**
     * 发布，默认qos为0，非持久化
     *
     * @param topic       主题
     * @param pushMessage 推送消息
     */
    public void publish(String topic, String pushMessage) {

        publish(1, false, topic, pushMessage);

    }

    /**
     * 发布
     *
     * @param qos         服务质量
     * @param retained    是否持久化
     * @param topic       主题
     * @param pushMessage 消息
     */
    public void publish(int qos, boolean retained, String topic, String pushMessage) {
        MqttMessage message = new MqttMessage();
        message.setQos(qos);
        message.setRetained(retained);
        message.setPayload(pushMessage.getBytes(StandardCharsets.UTF_8));
        MqttTopic mTopic = MqttConnection.getClient().getTopic(topic);
        if (null == mTopic) {
            log.error("=======MqttProducerHelper======= topic 不存在");
            return;
        }
        MqttDeliveryToken token;
        try {
            token = mTopic.publish(message);
            token.waitForCompletion();
        } catch (MqttException e) {
            log.error("=======MqttProducerHelper======= publish failed", e);
        }
    }

    /**
     * 发布消息的服务质量(推荐为：2-确保消息到达一次。0-至多一次到达；1-至少一次到达，可能重复)，
     * retained 默认：false-非持久化（是指一条消息消费完，就会被删除；持久化，消费完，还会保存在服务器中，当新的订阅者出现，继续给新订阅者消费）
     *
     * @param topic       主题
     * @param pushMessage 消息
     */
    public void publish(int qos, String topic, String pushMessage) {
        publish(qos, false, topic, pushMessage);
    }


}
