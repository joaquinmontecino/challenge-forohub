package com.alura.forohub.domain.dto.user;

import com.alura.forohub.domain.models.Role;
import com.alura.forohub.domain.models.User;

public record UserDataDTO(
        Long id,
        String name,
        String email,
        Role role,
        Boolean active
) {

    public UserDataDTO(User user){
        this(user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole(),
                user.getActive());
    }
}
