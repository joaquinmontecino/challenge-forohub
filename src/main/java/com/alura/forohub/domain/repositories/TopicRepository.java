package com.alura.forohub.domain.repositories;

import com.alura.forohub.domain.models.Category;
import com.alura.forohub.domain.models.Status;
import com.alura.forohub.domain.models.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicRepository extends JpaRepository<Topic,Long> {

    Boolean existsByTitleAndMessage(String title, String message);

    Page<Topic> findAllByStatusIsNot(Status status, Pageable pageable);

    Page<Topic> findAllByAuthorId(Long id, Pageable pageable);

    Page<Topic> findAllByCourseId(Long id, Pageable pageable);
}
