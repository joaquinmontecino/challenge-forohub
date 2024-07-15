package com.alura.forohub.domain.repositories;

import com.alura.forohub.domain.models.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.alura.forohub.domain.models.Course;
import org.springframework.stereotype.Repository;


@Repository
public interface CourseRepository extends JpaRepository<Course,Long> {
    Page<Course> findAllByCategoryIs(Category category, Pageable pageable);
}
