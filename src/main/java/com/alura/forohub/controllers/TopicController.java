package com.alura.forohub.controllers;

import com.alura.forohub.domain.dto.reply.ReplyDataDTO;
import com.alura.forohub.domain.dto.topic.TopicCreationDTO;
import com.alura.forohub.domain.dto.topic.TopicDataDTO;
import com.alura.forohub.domain.dto.topic.TopicUpdateDTO;
import com.alura.forohub.domain.models.Category;
import com.alura.forohub.domain.models.Course;
import com.alura.forohub.domain.services.TopicService;
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
@RequestMapping("/topics")
@SecurityRequirement(name = "bearer-key")
@Tag(name = "Tópico")
public class TopicController {

    @Autowired
    private TopicService topicService;

    @PostMapping
    @Transactional
    @Operation(summary = "Crear un nuevo topico")
    public ResponseEntity<TopicDataDTO> createTopic(@RequestBody @Valid TopicCreationDTO topicCreationDTO,
                                                    UriComponentsBuilder uriComponentsBuilder){
        var topic = topicService.createTopic(topicCreationDTO);
        var uri = uriComponentsBuilder.path("/{id}").buildAndExpand(topic.getId()).toUri();
        return ResponseEntity.created(uri).body(new TopicDataDTO(topic));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Lista un topico a partir de su id..")
    public ResponseEntity<TopicDataDTO> getTopicById(@PathVariable Long id){
        return ResponseEntity.ok(topicService.getTopicById(id));
    }

    @GetMapping
    @Operation(summary = "Lista todos los topicos que no han sido cerrados.")
    public ResponseEntity<Page<TopicDataDTO>> getOpenTopics(@PageableDefault(size = 5, sort = {"id"}) Pageable pageable){
        return ResponseEntity.ok(topicService.getOpenTopics(pageable));
    }

    @GetMapping("/all")
    @Operation(summary = "Lista todos los topicos.")
    public ResponseEntity<Page<TopicDataDTO>> getAllTopics(@PageableDefault(size = 5, sort = {"id"}) Pageable pageable){
        return ResponseEntity.ok(topicService.getAllTopics(pageable));
    }

    @GetMapping("/course/{id}")
    @Operation(summary = "Lista todos los topicos de un curso")
    public ResponseEntity<Page<TopicDataDTO>> getTopicsByCourse(@PathVariable Long id,
                                                                  @PageableDefault(size = 5, sort = {"id"}) Pageable pageable){
        return ResponseEntity.ok(topicService.getTopicsByCourse(id, pageable));
    }

    @GetMapping("/replies/{id}")
    @Operation(summary = "Lista todas las respuestas de un topico")
    public ResponseEntity<Page<ReplyDataDTO>> getRepliesByTopic(@PathVariable Long id,
                                                                @PageableDefault(size = 5, sort = {"id"}) Pageable pageable){
        return ResponseEntity.ok(topicService.getRepliesByTopicId(id, pageable));
    }

    @PutMapping("/{id}")
    @Transactional
    @Operation(summary = "Modifica un topico según su id")
    public ResponseEntity<TopicDataDTO> updateTopic(@RequestBody @Valid TopicUpdateDTO topicUpdateDTO, @PathVariable Long id){
        return ResponseEntity.ok(topicService.updateTopic(topicUpdateDTO, id));
    }

    @DeleteMapping("/{id}")
    @Transactional
    @Operation(summary = "Cierra un topico según su id")
    public ResponseEntity<?> closeTopic(@PathVariable Long id){
        topicService.closeTopic(id);
        return ResponseEntity.noContent().build();
    }


}
