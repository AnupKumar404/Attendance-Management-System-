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
//    @Query(value = "SELECT * FROM Users LEFT JOIN Student ON Users.id = Student.user_id", nativeQuery = true)
//    List<User> findAllUsers();
}