package com.alura.forohub.controllers;

import com.alura.forohub.domain.dto.reply.ReplyCreationDTO;
import com.alura.forohub.domain.dto.reply.ReplyDataDTO;
import com.alura.forohub.domain.dto.reply.ReplyUpdateDto;
import com.alura.forohub.domain.services.ReplyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/replies")
@SecurityRequirement(name = "bearer-key")
@Tag(name = "Respuestas")
public class ReplyController {

    @Autowired
    private ReplyService replyService;


    @PostMapping
    @Transactional
    @Operation(summary = "Crea una nueva respuesta")
    public ResponseEntity<ReplyDataDTO> createReply(@RequestBody @Valid ReplyCreationDTO replyCreationDTO,
                                                    UriComponentsBuilder uriComponentsBuilder){
        var reply = replyService.createReply(replyCreationDTO);
        var uri = uriComponentsBuilder.path("/{id}").buildAndExpand(reply.getId()).toUri();

        return ResponseEntity.created(uri).body(new ReplyDataDTO(reply));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Lista una respuesta por id")
    public ResponseEntity<ReplyDataDTO> getReplyById(@PathVariable Long id){
        return ResponseEntity.ok(replyService.getReplyById(id));
    }

    @PutMapping("/edit/{id}")
    @Transactional
    @Operation(summary = "Modifica una respuesta por su id")
    public ResponseEntity<ReplyDataDTO> editReply(@RequestBody @Valid ReplyUpdateDto replyUpdateDto, @PathVariable Long id){
        return ResponseEntity.ok(replyService.editReply(replyUpdateDto, id));
    }

    @PutMapping("/solution/{id}")
    @Transactional
    @Operation(summary = "Marca una respuesta como la solución del topico")
    public ResponseEntity<ReplyDataDTO> markReplyAsSolution(@PathVariable Long id){
        return ResponseEntity.ok(replyService.markReplyAsSolution(id));
    }

    @DeleteMapping("/{id}")
    @Transactional
    @Operation(summary = "Elimina una respuesta por su id")
    public ResponseEntity<?> deleteReply(@PathVariable Long id){
        replyService.deleteReply(id);
        return ResponseEntity.noContent().build();
    }
}
