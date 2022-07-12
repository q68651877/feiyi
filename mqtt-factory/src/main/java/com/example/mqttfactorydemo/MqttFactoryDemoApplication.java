package com.example.mqttfactorydemo;

import com.example.mqttfactorydemo.config.MqttConfiguration;
import com.example.mqttfactorydemo.mqtt.MqttConnection;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@Slf4j
@SpringBootApplication
@EnableAsync
@EnableScheduling
public class MqttFactoryDemoApplication implements ApplicationRunner {
    @Autowired
    private MqttConfiguration mqttConfiguration;
    public static void main(String[] args) {
        SpringApplication.run(MqttFactoryDemoApplication.class, args);
    }


    /**
     * mqtt 初始化
     * @param args
     * @throws Exception
     */
    @Override
    public void run(ApplicationArguments args) throws Exception {
        boolean isStart = true;
        if(isStart){
            if (log.isInfoEnabled()){
                log.info("===============>>>Mqtt is run starting:<<==================");
            }
            MqttConnection mqttConnection = new MqttConnection();
            mqttConnection.connect(mqttConfiguration);
        }
    }
}
