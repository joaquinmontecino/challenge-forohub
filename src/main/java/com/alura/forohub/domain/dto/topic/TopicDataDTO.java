package com.alura.forohub.domain.dto.topic;

import com.alura.forohub.domain.models.Category;
import com.alura.forohub.domain.models.Status;
import com.alura.forohub.domain.models.Topic;

import java.time.LocalDateTime;

public record TopicDataDTO(
        Long id,
        String title,
        String message,
        LocalDateTime creationDate,
        Status status,
        String author,
        String courseName,
        Category courseCategory
) {
    public TopicDataDTO(Topic topic){
        this(topic.getId(),
                topic.getTitle(),
                topic.getMessage(),
                topic.getCreationDate(),
                topic.getStatus(),
                topic.getAuthor().getName(),
                topic.getCourse().getCourseName(),
                topic.getCourse().getCategory()
                );
    }
}
