package com.alura.forohub.domain.repositories;

import com.alura.forohub.domain.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;


public interface UserRepository extends JpaRepository<User,Long> {
    UserDetails findByEmail(String email);

    Page<User> findAllByActiveTrue(Pageable pageable);
}
