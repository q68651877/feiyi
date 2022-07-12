package com.example.mqttfactorydemo.service;

import com.example.mqttfactorydemo.util.DateUtil;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author peiyuxiang
 * @date 2022/7/12
 */
@Component
public class Task {
    @Scheduled(cron="0/5 * *  * * ? ")   //每5秒执行一次
    public void execute(){
        SimpleDateFormat df = new SimpleDateFormat(DateUtil.DATE_FORMAT_SECOND_COMPRESS); //设置日期格式
        System.out.println("=======Task======= 定时任务测试 "  + df.format(new Date()));
    }

}
