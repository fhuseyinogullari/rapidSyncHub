package com.github.fhuseyinogullari.rapidsynchub.controller;

import com.github.fhuseyinogullari.rapidsynchub.entity.ChatMessage;
import com.github.fhuseyinogullari.rapidsynchub.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    private ChatService chatService;

    @Autowired
    public ChatController(ChatService theChatService) {
        chatService = theChatService;
    }

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
        System.out.println("sendMessage"+ "[ " + chatMessage + " ]");
        chatService.save(chatMessage);
        return chatMessage;
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public ChatMessage addUser(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
        // Add username in web socket session
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        System.out.println("addUser"+ "[ " + chatMessage + " ]");
        chatService.save(chatMessage);
        return chatMessage;
    }
}