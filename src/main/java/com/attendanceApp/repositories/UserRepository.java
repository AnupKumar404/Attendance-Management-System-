package com.attendanceApp.repositories;

import com.attendanceApp.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    @Query(value = "SELECT * FROM users WHERE full_name = ?", nativeQuery = true)
    Optional<User> findByFullname(String name);

    boolean existsByUsername(String username);
    @Query(value = "SELECT id, username, full_name FROM users", nativeQuery = true)
    Page<User> findAllUsers(Pageable pageable);
}