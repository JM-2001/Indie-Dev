package com.csc340.IndieDev.message;

import com.csc340.IndieDev.project.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {

    List<Message> findByAuthorId(Long authorId);

    List<Message> findByAuthorIdOrRecipientId(Long authorId, Long recipientId);

    List<Message> findByAuthorIdAndRecipientIdOrAuthorIdAndRecipientId(
            Long authorId1, Long recipientId1, Long authorId2, Long recipientId2);

    List<Message> findTop10ByOrderByCreatedAtDesc();

    List<Message> findTop10ByAuthorIdOrRecipientIdOrderByCreatedAtDesc(Long authorId, Long recipientId);
}

