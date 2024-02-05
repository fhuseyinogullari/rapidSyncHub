package com.github.fhuseyinogullari.rapidsynchub.service;

import com.github.fhuseyinogullari.rapidsynchub.dao.ChatMessageDAO;
import com.github.fhuseyinogullari.rapidsynchub.entity.ChatMessage;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatMessageService {

    private ChatMessageDAO chatMessageDAO;
    @Autowired
    public ChatMessageService(ChatMessageDAO theChatMessageDAO) {
        chatMessageDAO = theChatMessageDAO;
    }

    public List<ChatMessage> findAll() {
        return chatMessageDAO.findAll();
    }

    public ChatMessage findById(int theId) {
        return chatMessageDAO.findById(theId);
    }

    @Transactional
    public ChatMessage save(ChatMessage theChatMessage) {
        return chatMessageDAO.save(theChatMessage);
    }

    @Transactional
    public void deleteById(int theId) {
        chatMessageDAO.deleteById(theId);
    }
}
