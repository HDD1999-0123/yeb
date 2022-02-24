package com.hdd.server.controller;

import com.hdd.server.pojo.Admin;
import com.hdd.server.pojo.CharMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;

/**
 * @author hedd
 * @create 2021/6/15 16:32
 * @Desc websocket的controller类
 */
@Controller
public class WsController {
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/ws/chat")
    public void handleMsg(Authentication authentication, CharMsg charMsg){
        Admin admin = (Admin) authentication.getPrincipal();
        charMsg.setFrom(admin.getUsername());
        charMsg.setFromNickName(admin.getName());
        charMsg.setDate(LocalDateTime.now());
        simpMessagingTemplate.convertAndSendToUser(charMsg.getTo(),"/queue/chat",charMsg);
    }
}
