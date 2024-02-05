package com.github.fhuseyinogullari.rapidsynchub.controller;

import com.github.fhuseyinogullari.rapidsynchub.entity.ChatMessage;
import com.github.fhuseyinogullari.rapidsynchub.entity.User;
import com.github.fhuseyinogullari.rapidsynchub.service.ChatMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    private ChatMessageService chatService;
    @Autowired
    public ChatController(ChatMessageService theChatService) {
        chatService = theChatService;
    }

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
        chatMessage = chatService.save(chatMessage);
        System.out.println("sendMessage"+ "[ " + chatMessage.toString() + " ]");
        return chatMessage;
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public ChatMessage addUser(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
        // Add username in web socket session
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        chatMessage = chatService.save(chatMessage);
        System.out.println(chatMessage.toString());
        return chatMessage;
    }

    @MessageMapping("/greetings")
    @SendToUser("/queue/greetings")
    public String reply(@Payload ChatMessage message, User user) {
        return  "Hello " + message.getContent();
    }
}