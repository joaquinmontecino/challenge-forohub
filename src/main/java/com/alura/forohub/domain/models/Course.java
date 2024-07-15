package com.alura.forohub.domain.models;

import com.alura.forohub.domain.dto.course.CourseCreationDTO;
import com.alura.forohub.domain.dto.course.CourseUpdateDTO;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "course")
@Entity(name = "Course")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "course_name")
    private String courseName;
    @Enumerated(EnumType.STRING)
    private Category category;
    private Boolean active;

    public Course(CourseCreationDTO courseCreationDTO){
        this.courseName = courseCreationDTO.courseName();
        this.category = courseCreationDTO.category();
        this.active = true;
    }

    public void editCourse(CourseUpdateDTO courseUpdateDTO){
        if (courseUpdateDTO.courseName() != null) this.courseName = courseUpdateDTO.courseName();
        if (courseUpdateDTO.category() != null) this.category = courseUpdateDTO.category();
    }

    public void removeCourse(){
        this.active = false;
    }
}
