package com.attendanceApp.repositories;

import com.attendanceApp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.username = :username")
    Optional<User> findByUsername(String username);
    boolean existsByUsername(String username);
    @Query(value = "SELECT u.id, u.username, s.roll_no FROM users u JOIN student s ON s.user_id = u.id", nativeQuery = true)
    List<User> findAllUsers();
}