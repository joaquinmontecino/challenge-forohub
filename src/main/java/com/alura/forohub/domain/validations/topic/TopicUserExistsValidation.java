package com.alura.forohub.domain.validations.topic;

import com.alura.forohub.domain.dto.topic.TopicCreationDTO;
import com.alura.forohub.domain.repositories.UserRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TopicUserExistsValidation implements CreateTopicValidation{

    @Autowired
    private UserRepository userRepository;

    @Override
    public void validate(TopicCreationDTO data) {
        if(!userRepository.existsById(data.authorId())){
            throw new ValidationException("El usuario ingresado no existe");
        }
        var user = userRepository.findById(data.authorId())
                .orElseThrow(() -> new ValidationException("El usuario ingresado no existe"));

        if (!user.getActive()) {
            throw new ValidationException("El usuario ingresado fue deshabilitado");
        }
    }
}
