package com.example.mqttfactorydemo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.mqttfactorydemo.mqtt.MqttConsumerHelper;
import com.example.mqttfactorydemo.mqtt.MqttProducerHelper;
import com.example.mqttfactorydemo.core.Result;
import com.example.mqttfactorydemo.core.ResultGenerator;
import com.example.mqttfactorydemo.model.MqttMessageBO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author peiyuxiang
 * @date 2022/6/20
 */
@RestController
@Slf4j
@ResponseBody
@RequestMapping("/mqtt")
public class MqttController {

    @Resource
    private MqttProducerHelper mqttProducerHelper;

    @Resource
    private MqttConsumerHelper mqttConsumerHelper;

    /**
     * 发布消息
     *
     * @return 响应体
     */
    @PostMapping("/send")
    public Result sendMqtt(@RequestBody MqttMessageBO mqttMessageBO) {

        String topic = mqttMessageBO.getTopic();
        String payload = mqttMessageBO.getPayload();
//        String json = "{'gender':'man','hobby':'girl'}";
        log.info("    本机主题:" + topic + " 发送数据为:" + JSONObject.toJSONString(payload));
        mqttProducerHelper.send(topic, payload);
        log.info("=======》发送结束");
        return ResultGenerator.genSuccessResult("发送结束");
    }

    /**
     * 消费者订阅主题
     * @param mqttMessageBO 消息实体
     * @return 响应体
     */
    @PostMapping("/subscript")
    public Result subscript(@RequestBody MqttMessageBO mqttMessageBO) {
        String[] topics = mqttMessageBO.getTopics();
        mqttConsumerHelper.subscribe(topics);
        return ResultGenerator.genSuccessResult("订阅主题");
    }

    /**
     * 取消订阅
     * @param mqttMessageBO 消息实体
     * @return 响应体
     */
    @RequestMapping("/unsubscribe")
    public Result unsubscribe( @RequestBody MqttMessageBO mqttMessageBO){
       mqttConsumerHelper.cleanTopic(mqttMessageBO.getTopic());
        return ResultGenerator.genSuccessResult("取消订阅主题");
    }


}
