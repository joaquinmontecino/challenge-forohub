package com.alura.forohub.domain.repositories;

import com.alura.forohub.domain.models.Reply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReplyRepository extends JpaRepository<Reply,Long> {

    Page<Reply> findAllByAuthorId(Long id, Pageable pageable);

    Page<Reply> findAllByTopicId(Long id, Pageable pageable);
}
