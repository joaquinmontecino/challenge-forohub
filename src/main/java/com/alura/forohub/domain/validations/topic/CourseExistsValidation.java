package com.alura.forohub.domain.validations.topic;

import com.alura.forohub.domain.dto.topic.TopicCreationDTO;
import com.alura.forohub.domain.repositories.CourseRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CourseExistsValidation implements CreateTopicValidation{

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public void validate(TopicCreationDTO data) {
        if(!courseRepository.existsById(data.courseId())){
            throw new ValidationException("El curso ingresado no existe");
        }

    }
}
