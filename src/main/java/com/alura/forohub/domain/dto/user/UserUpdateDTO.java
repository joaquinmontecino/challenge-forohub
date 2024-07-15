package com.alura.forohub.domain.dto.user;

import com.alura.forohub.domain.models.Role;

public record UserUpdateDTO(
        String name,
        String password,
        Role role
) {
}
