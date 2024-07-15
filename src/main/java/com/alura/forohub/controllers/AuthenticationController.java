package com.alura.forohub.controllers;

import com.alura.forohub.domain.dto.user.UserCredentialsDTO;
import com.alura.forohub.domain.models.User;
import com.alura.forohub.infra.security.JWTtokenData;
import com.alura.forohub.infra.security.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@Tag(name = "Inicio de sesión")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    @Operation(summary = "Inicia sesión para obtener un token de acceso")
    public ResponseEntity authenticateUser(@RequestBody @Valid UserCredentialsDTO userCredentialsDTO){
        Authentication authToken = new UsernamePasswordAuthenticationToken(
                userCredentialsDTO.email(), userCredentialsDTO.password());
        var authenticatedUser = authenticationManager.authenticate(authToken);
        var JWTtoken = tokenService.generateToken((User) authenticatedUser.getPrincipal());
        return ResponseEntity.ok(new JWTtokenData(JWTtoken));


    }
}
