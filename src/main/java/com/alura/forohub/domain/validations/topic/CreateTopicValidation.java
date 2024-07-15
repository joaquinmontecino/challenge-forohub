package com.alura.forohub.domain.validations.topic;

import com.alura.forohub.domain.dto.topic.TopicCreationDTO;

public interface CreateTopicValidation {
    public void validate(TopicCreationDTO data);
}
