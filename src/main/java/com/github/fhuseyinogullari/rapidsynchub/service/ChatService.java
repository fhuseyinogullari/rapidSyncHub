package com.github.fhuseyinogullari.rapidsynchub.service;

import com.github.fhuseyinogullari.rapidsynchub.dto.ChatSaveDAO;
import com.github.fhuseyinogullari.rapidsynchub.entity.ChatMessage;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

    private ChatSaveDAO chatSaveDAO;

    @Autowired
    public ChatService(ChatSaveDAO theChatSaveDAO) {
        chatSaveDAO = theChatSaveDAO;
    }
    @Transactional
    public ChatMessage save(ChatMessage theChatMessage) {
        return chatSaveDAO.save(theChatMessage);
    }

}
