package com.alura.forohub.domain.dto.reply;

import com.alura.forohub.domain.models.Reply;

import java.time.LocalDateTime;

public record ReplyDataDTO(
        Long id,
        String message,
        Long topicId,
        String topic,
        LocalDateTime creationDate,
        Long authorId,
        String author,
        Boolean solution
) {
    public ReplyDataDTO(Reply reply){
        this(reply.getId(),
                reply.getMessage(),
                reply.getTopic().getId(),
                reply.getTopic().getTitle(),
                reply.getCreationDate(),
                reply.getAuthor().getId(),
                reply.getAuthor().getName(),
                reply.getSolution());
    }
}
