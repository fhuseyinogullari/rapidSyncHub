package com.github.fhuseyinogullari.rapidsynchub.dto;

import com.github.fhuseyinogullari.rapidsynchub.entity.ChatMessage;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ChatSaveDAO {
    private EntityManager entityManager;


    @Autowired
    public ChatSaveDAO(EntityManager theEntityManager) {
        entityManager = theEntityManager;
    }

    public ChatMessage save(ChatMessage theChatMessage) {
        ChatMessage message = entityManager.merge(theChatMessage);
        return message;
    }
}
