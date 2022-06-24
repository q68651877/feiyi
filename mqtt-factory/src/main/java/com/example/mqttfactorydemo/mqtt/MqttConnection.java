package com.example.mqttfactorydemo.mqtt;

import com.example.mqttfactorydemo.config.MqttConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttAsyncClient;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

/**
 * MQTT 连接帮助类
 * @author peiyuxiang
 * @date 2022/6/20
 */
@Slf4j
public class MqttConnection {
    private static MqttClient client;

    public static MqttClient getClient() {
        return client;
    }

    public static void setClient(MqttClient client) {
        MqttConnection.client = client;
    }


    /**
     * 编辑连接信息
     *
     * @param userName  用户名
     * @param password  密码
     * @param outTime   超时时间
     * @param keepAlive 心跳时间
     * @return 连接数据
     */
    private MqttConnectOptions getOption(String userName, String password, int outTime, int keepAlive) {
        //MQTT连接设置
        MqttConnectOptions option = new MqttConnectOptions();
        //设置是否清空session,false表示服务器会保留客户端的连接记录，true表示每次连接到服务器都以新的身份连接
        option.setCleanSession(false);
        //设置连接的用户名
        option.setUserName(userName);
        //设置连接的密码
        option.setPassword(password.toCharArray());
        //设置超时时间 单位为秒
        option.setConnectionTimeout(outTime);
        //设置会话心跳时间 单位为秒 服务器会每隔(1.5*keepTime)秒的时间向客户端发送个消息判断客户端是否在线，但这个方法并没有重连的机制
        option.setKeepAliveInterval(keepAlive);
        //setWill方法，如果项目中需要知道客户端是否掉线可以调用该方法。设置最终端口的通知消息
        //option.setWill(topic, "close".getBytes(StandardCharsets.UTF_8), 2, true);
        option.setMaxInflight(1000);
        return option;
    }

    /**
     * 发起连接
     */
    public void connect(MqttConfiguration mqttConfiguration) {
        MqttClient client;
        try {
            client = new MqttClient(mqttConfiguration.getHost(),
                    MqttAsyncClient.generateClientId(),
                    new MemoryPersistence());
            MqttConnectOptions options = getOption(mqttConfiguration.getUsername(),
                    mqttConfiguration.getPassword(),
                    mqttConfiguration.getTimeout(),
                    mqttConfiguration.getKeepAlive());
            MqttConnection.setClient(client);
            try {
                client.setCallback(new MqttPushCallback(this, mqttConfiguration));
                if (!client.isConnected()) {
                    client.connect(options);
                    log.info("=======MqttConnection======= MQTT连接成功");

                } else {//这里的逻辑是如果连接不成功就重新连接
                    client.disconnect();
                    client.connect(options);
                    log.info("=======MqttConnection======= MQTT断连成功");
                }
            } catch (Exception e) {
                log.error("=======MqttConnection======= MQTT连接失败", e);
            }
        } catch (Exception e) {
            log.error("=======MqttConnection======= MQTT连接失败", e);
        }
    }

    /**
     * 断线重连
     *
     * @throws Exception
     */
    public Boolean reConnect() throws Exception {
        boolean isConnected = false;
        if (null != client) {
            client.connect();
            if (client.isConnected()) {
                isConnected = true;
            }
        }
        return isConnected;
    }

}
