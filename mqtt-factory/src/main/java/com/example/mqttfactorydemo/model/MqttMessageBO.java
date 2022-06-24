package com.example.mqttfactorydemo.model;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author peiyuxiang
 * @date 2022/6/15
 */
@Data
@Accessors(chain = true)
public class MqttMessageBO {
    /**
     * 主题列表
     */
    private String[] topics;
    /**
     * 内容
     */
    private String payload;

    /**
     * 主题
     */
    private String topic;
}
