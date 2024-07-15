package com.alura.forohub.domain.dto.course;

import com.alura.forohub.domain.models.Category;
import com.alura.forohub.domain.models.Course;

public record CourseDataDTO(
        Long id,
        String courseName,
        Category category
) {
    public CourseDataDTO(Course course){
        this(course.getId(), course.getCourseName(), course.getCategory());
    }
}
