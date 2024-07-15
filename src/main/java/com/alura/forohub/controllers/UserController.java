package com.alura.forohub.controllers;

import com.alura.forohub.domain.dto.reply.ReplyDataDTO;
import com.alura.forohub.domain.dto.topic.TopicDataDTO;
import com.alura.forohub.domain.dto.user.UserCreationDTO;
import com.alura.forohub.domain.dto.user.UserDataDTO;
import com.alura.forohub.domain.dto.user.UserUpdateDTO;
import com.alura.forohub.domain.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/users")
@SecurityRequirement(name = "bearer-key")
@Tag(name = "Usuario", description = "User description")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/{id}")
    @Operation(summary = "Lista un usuario a partir de su id")
    public ResponseEntity<UserDataDTO> getUserById(@PathVariable Long id){
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @GetMapping
    @Operation(summary = "Lista todos los usuarios activos")
    public ResponseEntity<Page<UserDataDTO>> getActiveUsers(@PageableDefault(size = 5, sort = {"id"}) Pageable pageable){
        return ResponseEntity.ok(userService.getActiveUsers(pageable));
    }

    @GetMapping("/all")
    @Operation(summary = "Lista todos los usuarios")
    public ResponseEntity<Page<UserDataDTO>> getAllUsers(@PageableDefault(size = 5, sort = {"id"}) Pageable pageable){
        return ResponseEntity.ok(userService.getAllUsers(pageable));
    }

    @GetMapping("/topics/{id}")
    @Operation(summary = "Lista todos los topicos creados por un usuario")
    public ResponseEntity<Page<TopicDataDTO>> getAllTopicsByUser(@PathVariable Long id, @PageableDefault(size = 5, sort = {"id"}) Pageable pageable){
        return ResponseEntity.ok(userService.getAllTopicsByUser(id, pageable));
    }

    @GetMapping("/replies/{id}")
    @Operation(summary = "Lista todas las respuestas por un usuario")
    public ResponseEntity<Page<ReplyDataDTO>> getAllRepliesByUser(@PathVariable Long id, @PageableDefault(size = 5, sort = {"id"}) Pageable pageable){
        return ResponseEntity.ok(userService.getAllRepliesByUser(id,pageable));
    }

    @PutMapping("/{id}")
    @Transactional
    @Operation(summary = "Actualiza los datos de un usuario")
    public ResponseEntity<UserDataDTO> updateUser(@RequestBody @Valid UserUpdateDTO userUpdateDTO, @PathVariable Long id){
        return ResponseEntity.ok(userService.updateUser(userUpdateDTO, id));
    }

    @DeleteMapping("/{id}")
    @Transactional
    @Operation(summary = "Remueve un usuario del sistema")
    public ResponseEntity<?> removeUser(@PathVariable Long id){
        userService.removeUser(id);
        return ResponseEntity.noContent().build();
    }
}
