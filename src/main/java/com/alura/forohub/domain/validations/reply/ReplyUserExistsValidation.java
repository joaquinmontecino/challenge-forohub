package com.alura.forohub.domain.validations.reply;

import com.alura.forohub.domain.dto.reply.ReplyCreationDTO;
import com.alura.forohub.domain.repositories.UserRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReplyUserExistsValidation implements CreateReplyValidation{
    @Autowired
    private UserRepository userRepository;

    @Override
    public void validate(ReplyCreationDTO data) {
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
