package com.alura.forohub.domain.services;

import com.alura.forohub.domain.dto.course.CourseCreationDTO;
import com.alura.forohub.domain.dto.course.CourseDataDTO;
import com.alura.forohub.domain.dto.course.CourseUpdateDTO;
import com.alura.forohub.domain.models.Category;
import com.alura.forohub.domain.models.Course;
import com.alura.forohub.domain.repositories.CourseRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;


    public Course createCourse(CourseCreationDTO courseCreationDTO) {
        var course = new Course(courseCreationDTO);
        courseRepository.save(course);
        return course;
    }

    public Page<CourseDataDTO> getAllCourses(Pageable pageable) {
        return courseRepository.findAll(pageable).map(CourseDataDTO::new);
    }

    public CourseDataDTO getCourseById(Long id) {
        return new CourseDataDTO(courseRepository.getReferenceById(id));
    }

    public Page<CourseDataDTO> getCourseByCategory(Category category, Pageable pageable) {
        return courseRepository.findAllByCategoryIs(category, pageable).map(CourseDataDTO::new);
    }

    public CourseDataDTO updateCourse(CourseUpdateDTO courseUpdateDTO, Long id) {
        var course = courseRepository.findById(id)
                .orElseThrow(() -> new ValidationException("El curso ingresado no existe"));
        course.editCourse(courseUpdateDTO);
        return new CourseDataDTO(course);
    }

    public void removeCourse(Long id){
        var course = courseRepository.findById(id)
                .orElseThrow(() -> new ValidationException("El curso ingresado no existe"));
        course.removeCourse();
    }


}
