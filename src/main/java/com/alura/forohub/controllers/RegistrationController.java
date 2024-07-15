package com.alura.forohub.controllers;

import com.alura.forohub.domain.dto.user.UserCreationDTO;
import com.alura.forohub.domain.dto.user.UserDataDTO;
import com.alura.forohub.domain.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/register")
@Tag(name = "Registro de usuario")
public class RegistrationController {

    @Autowired
    private UserService userService;

    @PostMapping
    @Transactional
    @Operation(summary = "Registrar un nuevo usuario")
    public ResponseEntity<UserDataDTO> registerUser(@RequestBody @Valid UserCreationDTO userCreationDTO,
                                                    UriComponentsBuilder uriComponentsBuilder){
        var user = userService.registerUser(userCreationDTO);
        var uri = uriComponentsBuilder.path("/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(new UserDataDTO(user));
    }
}
