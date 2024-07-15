package com.alura.forohub.domain.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserCreationDTO (
        @NotBlank String name,
        @NotBlank @Email String email,
        @NotBlank String password
){
}
