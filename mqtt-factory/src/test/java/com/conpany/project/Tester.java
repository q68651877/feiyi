package com.conpany.project;


import com.example.mqttfactorydemo.MqttFactoryDemoApplication;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * 单元测试继承该类即可
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MqttFactoryDemoApplication.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

@Transactional
@Rollback
public abstract class Tester {

    @Test
    public void test(){
        System.out.println("!");
    }
}



