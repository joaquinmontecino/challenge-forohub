package com.alura.forohub.domain.dto.reply;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ReplyCreationDTO(
        @NotBlank String message,
        @NotNull Long authorId,
        @NotNull Long topicId
) {
}
