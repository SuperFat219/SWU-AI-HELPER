package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private com.example.demo.config.WebSocket webSocket;

    @RequestMapping("/test_conn")
    public void test_conn() {
        //要从后端页面发送消息
        try {
            Thread.sleep(3000);
            //给前端发送消息
            webSocket.sendMessage("客户端你好，这是服务端发送的消息");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

