package com.github.fhuseyinogullari.rapidsynchub.config;

import com.github.fhuseyinogullari.rapidsynchub.enums.MessageType;
import com.github.fhuseyinogullari.rapidsynchub.entity.ChatMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Component;
import org.springframework.context.event.EventListener;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;

@Component
public class WebSocketEventListener {

    private SimpMessageSendingOperations messagingTemplate;

    @Autowired
    public WebSocketEventListener(SimpMessageSendingOperations messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        // Left event can be saved by DB
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        String username = (String) headerAccessor.getSessionAttributes().get("username");
        if (username != null) {
            System.out.println("User disconnected: " + username);
            ChatMessage chatMessage = new ChatMessage(MessageType.LEAVE, username);
            messagingTemplate.convertAndSend("/topic/public", chatMessage);
        }
    }
}
