package com.alura.forohub.domain.dto.topic;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TopicCreationDTO(
        @NotNull Long authorId,
        @NotNull Long courseId,
        @NotBlank String title,
        @NotBlank String message
) {
}
