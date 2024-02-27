package com.csc340.IndieDev.message;

import com.csc340.IndieDev.user.User;
import com.csc340.IndieDev.user.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class MessageService {


    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserRepository userRepository;


    public void saveMessage(Message message) {
        User author = userRepository.findById(message.getAuthorId()).orElse(null);
        User recipient = userRepository.findById(message.getRecipientId()).orElse(null);

        if (author != null && recipient != null) {
            messageRepository.save(message);
        } else {
            throw new EntityNotFoundException("One or both users not found");
        }
    }


    public List<Message> getMessagesByUser(User user) {
        messageRepository.findByAuthorId(user.getId());
        return Collections.emptyList();
    }

    public List<Message> getMessagesBetweenUsers(User user1, User user2) {
        return messageRepository.findByAuthorIdAndRecipientIdOrAuthorIdAndRecipientId(
                user1.getId(), user2.getId(), user2.getId(), user1.getId());
    }

    public List<Message> getLatestMessages(Long userId) {

        return messageRepository.findTop10ByAuthorIdOrRecipientIdOrderByCreatedAtDesc(userId, userId);
    }

}
