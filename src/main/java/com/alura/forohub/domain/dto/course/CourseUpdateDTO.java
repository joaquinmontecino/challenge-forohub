package com.alura.forohub.domain.dto.course;

import com.alura.forohub.domain.models.Category;

public record CourseUpdateDTO(
        String courseName,
        Category category
) {
}
