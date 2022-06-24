package com.example.mqttfactorydemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author peiyuxiang
 * @date 2022/6/15
 */
@Controller
public class IndexController {
    @GetMapping("/index")
    private String index(){
        return "index";
    }


}
