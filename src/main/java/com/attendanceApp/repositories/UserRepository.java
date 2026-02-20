package com.attendanceApp.repositories;

import com.attendanceApp.entities.Users;
import com.attendanceApp.projections.UserProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

    Optional<Users> findByEmail(String email);

    Optional<UserProjection> findByFullName(String name);

    boolean existsByEmail(String username);

    @Query("SELECT isActive i FROM Users u WHERE u.fullName=:name")
    Optional<Boolean> isUserActive(String name);

    @Query(value = "SELECT email, full_name, role FROM users", nativeQuery = true)
    <T> Page<T> findAllUsers(Pageable pageable);
}