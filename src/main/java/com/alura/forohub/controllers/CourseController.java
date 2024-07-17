package com.alura.forohub.controllers;

import com.alura.forohub.domain.dto.course.CourseCreationDTO;
import com.alura.forohub.domain.dto.course.CourseDataDTO;
import com.alura.forohub.domain.dto.course.CourseUpdateDTO;
import com.alura.forohub.domain.models.Category;
import com.alura.forohub.domain.services.CourseService;
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
@RequestMapping("/courses")
@SecurityRequirement(name = "bearer-key")
@Tag(name = "Cursos")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping
    @Transactional
    @Operation(summary = "Crea un nuevo curso")
    public ResponseEntity<CourseDataDTO> createCourse(@RequestBody @Valid CourseCreationDTO courseCreationDTO,
                                                      UriComponentsBuilder uriComponentsBuilder){
        var course = courseService.createCourse(courseCreationDTO);
        var uri = uriComponentsBuilder.path("/{id}").buildAndExpand(course.getId()).toUri();

        return ResponseEntity.created(uri).body(new CourseDataDTO(course));
    }

    @GetMapping
    @Operation(summary = "Lista todos los cursos")
    public ResponseEntity<Page<CourseDataDTO>> getAllCourses(@PageableDefault(size = 5, sort = {"id"}) Pageable pageable){
        return ResponseEntity.ok(courseService.getAllCourses(pageable));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Lista un curso a partir de su id")
    public ResponseEntity<CourseDataDTO> getCourseById(@PathVariable Long id){
        return ResponseEntity.ok(courseService.getCourseById(id));
    }

    @GetMapping("/category/{course}")
    @Operation(summary = "Lista todos los cursos segun categoria")
    public ResponseEntity<Page<CourseDataDTO>> getCourseByCategory(@PathVariable @Valid Category category,
                                                                   @PageableDefault(size = 5, sort = {"id"}) Pageable pageable){
        return ResponseEntity.ok(courseService.getCourseByCategory(category, pageable));
    }

    @PutMapping("/{id}")
    @Transactional
    @Operation(summary = "Modifica un curso a partir de su id")
    public ResponseEntity<CourseDataDTO> updateCourse(@RequestBody @Valid CourseUpdateDTO courseUpdateDTO, @PathVariable Long id){
        return ResponseEntity.ok(courseService.updateCourse(courseUpdateDTO, id));
    }

    @DeleteMapping("/{id}")
    @Transactional
    @Operation(summary = "Remueve un curso a partir de su id")
    public ResponseEntity<?> removeCourse(@PathVariable Long id){
        courseService.removeCourse(id);
        return ResponseEntity.noContent().build();
    }
}
