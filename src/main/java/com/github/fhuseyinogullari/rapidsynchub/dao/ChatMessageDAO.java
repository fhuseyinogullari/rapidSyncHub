package com.github.fhuseyinogullari.rapidsynchub.dao;

import com.github.fhuseyinogullari.rapidsynchub.entity.ChatMessage;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ChatMessageDAO {
    private EntityManager entityManager;
    @Autowired
    public ChatMessageDAO(EntityManager theEntityManager) {
        entityManager = theEntityManager;
    }

    public List<ChatMessage> findAll() {
        TypedQuery<ChatMessage> theQuery = entityManager.createQuery("FROM ChatMessage", ChatMessage.class);

        List<ChatMessage> chatMessages = theQuery.getResultList();

        return chatMessages;
    }

    public ChatMessage findById(int theId) {
        ChatMessage theChatMessage = entityManager.find(ChatMessage.class, theId);

        return theChatMessage;
    }
    public ChatMessage save(ChatMessage theChatMessage) {
        ChatMessage message = entityManager.merge(theChatMessage);
        return message;
    }

    public void deleteById(int theId) {
        ChatMessage theChatMessage = entityManager.find(ChatMessage.class, theId);
        entityManager.remove(theChatMessage);
    }
}
