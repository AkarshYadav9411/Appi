package com.chat.appi.controller;

import com.chat.appi.model.ChatMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChatController {
    @MessageMapping("/sendmessage")
    @SendTo("/topic/messages")
    public ChatMessage sendMessage(ChatMessage message) {
        // Here you can add logic to save the message
        return message;
    }

    @GetMapping("/chat")
    public String chat() {
        return "chat";
    }
}
