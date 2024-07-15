package com.alura.forohub.domain.validations.user;

import com.alura.forohub.domain.dto.user.UserCreationDTO;
import com.alura.forohub.domain.repositories.UserRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DuplicatedUserValidation {

    @Autowired
    private UserRepository userRepository;

    public void validate(UserCreationDTO data){
        var duplicatedUser = userRepository.findByEmail(data.email());
        if (duplicatedUser != null){
            throw new ValidationException("Esta dirección de correo ya fue registrada anteriormente");
        }

    }
}
