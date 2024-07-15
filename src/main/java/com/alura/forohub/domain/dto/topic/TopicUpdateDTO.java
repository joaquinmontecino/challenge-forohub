package com.alura.forohub.domain.dto.topic;

import com.alura.forohub.domain.models.Status;

public record TopicUpdateDTO(
        String title,
        String message,
        Status status
) {
}
