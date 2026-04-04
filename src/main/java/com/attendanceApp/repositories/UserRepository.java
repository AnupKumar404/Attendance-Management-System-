package com.attendanceApp.repositories;

import com.attendanceApp.entities.User;
import com.attendanceApp.projections.LoginProjection;
import com.attendanceApp.projections.UserProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<LoginProjection> findByEmail(String email);

    Optional<UserProjection> findByFullName(String name);

    boolean existsByEmail(String username);

    @Query(value = "SELECT id, email, full_name, role, is_active FROM users", nativeQuery = true)
    Page<UserProjection> findAllUsers(Pageable pageable);
}