package com.example.mqttfactorydemo.mqtt;

import com.alibaba.fastjson.JSONObject;
import com.example.mqttfactorydemo.config.MqttConfiguration;
import com.example.mqttfactorydemo.controller.WebSockTest;
import com.example.mqttfactorydemo.util.Constants;
import com.example.mqttfactorydemo.util.SpringUtil;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author peiyuxiang
 * @date 2022/6/20
 */
@Slf4j
@Component
public class MqttPushCallback implements MqttCallback {
    private final MqttConnection client;
    private final MqttConfiguration mqttConfiguration;


    public MqttPushCallback(MqttConnection client, MqttConfiguration mqttConfiguration) {
        this.client = client;
        this.mqttConfiguration = mqttConfiguration;
    }

    @Override
    public void connectionLost(Throwable cause) {
        if (client != null) {
            while (true) {
                try {
                    log.info("=======MqttPushCallback======= 连接断开，5S之后尝试重连...");
                    Thread.sleep(5000);
                    MqttConnection mqttConsumerHelper = new MqttConnection();
                    mqttConsumerHelper.connect(mqttConfiguration);
                    if (MqttConnection.getClient().isConnected()) {
                        log.info("=======MqttPushCallback======= 重连成功");
                    }
                    break;
                } catch (Exception e) {
                    log.error("=======MqttPushCallback======= 连接断开，重连失败");
                    continue;
                }
            }
        }
        log.info(cause.getMessage());
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
        //publish后会执行到这里
        log.info("pushComplete==============>>>" + token.isComplete());
    }


    /**
     * 监听对应的主题消息
     *
     * @param topic   主题
     * @param message 内容
     * @throws Exception
     */
    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        // subscribe后得到的消息会执行到这里面
        String payload = new String(message.getPayload());
        log.info("============MqttPushCallback======= 接收消息主题 : {}", topic);
        log.info("============MqttPushCallback======= 接收消息Qos : {}", message.getQos());
        log.info("============MqttPushCallback======= 接收消息内容 : {}", payload);
        log.info("============MqttPushCallback======= 接收ID : {}", message.getId());
        // 发送到websocket
        JSONObject syncObj = new JSONObject();
        syncObj.put("topic", topic);
        syncObj.put("msg", payload);
        LocalDateTime now = LocalDateTime.now();
        syncObj.put("time", now);
        // 这里需要用getBean的形式，使用@Autowired会出错
        WebSockTest webSockTest = (WebSockTest) SpringUtil.getBean("webSocketTest");
        webSockTest.sendData(syncObj.toString(), Constants.SocketType.CJ);
    }


}
