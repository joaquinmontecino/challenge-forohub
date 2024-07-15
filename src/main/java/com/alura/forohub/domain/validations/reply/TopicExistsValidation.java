package com.alura.forohub.domain.validations.reply;

import com.alura.forohub.domain.dto.reply.ReplyCreationDTO;
import com.alura.forohub.domain.models.Status;
import com.alura.forohub.domain.repositories.TopicRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TopicExistsValidation implements CreateReplyValidation{

    @Autowired
    private TopicRepository topicRepository;

    @Override
    public void validate(ReplyCreationDTO data) {
        if(!topicRepository.existsById(data.topicId())){
            throw new ValidationException("El topico ingresado no existe");
        }

        var topic = topicRepository.findById(data.topicId())
                .orElseThrow(() -> new ValidationException("El topico ingresado no existe"));
        if(topic.getStatus() != Status.OPEN){
            throw new ValidationException("El topico ingresado no está abierto");
        }

    }
}
