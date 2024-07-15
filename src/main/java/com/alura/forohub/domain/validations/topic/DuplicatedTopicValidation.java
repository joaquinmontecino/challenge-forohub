package com.alura.forohub.domain.validations.topic;

import com.alura.forohub.domain.dto.topic.TopicCreationDTO;
import com.alura.forohub.domain.repositories.TopicRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DuplicatedTopicValidation implements CreateTopicValidation{

    @Autowired
    private TopicRepository topicRepository;

    @Override
    public void validate(TopicCreationDTO data) {
        if(topicRepository.existsByTitleAndMessage(data.title(), data.message())){
            throw new ValidationException("Ya existe un topico con ese titulo y mensaje");
        }

    }
}
