package com.alura.forohub.domain.dto.course;

import com.alura.forohub.domain.models.Category;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CourseCreationDTO(
        @NotBlank String courseName,
        @NotNull Category category

        ) {
}
