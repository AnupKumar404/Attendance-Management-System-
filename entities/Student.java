package com.spring.attendanceApp.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "students")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "student_name", nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String rollNo;

    @OneToOne
    @MapsId
    private User user;

    private String department;

    @JsonBackReference
    @ManyToMany(mappedBy = "students")
    private Set<Subject> subjects = new HashSet<>();
}
