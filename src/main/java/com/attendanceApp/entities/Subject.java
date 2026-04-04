package com.attendanceApp.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity(name = "subjects")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String code;

    @ManyToMany(mappedBy = "enrolledSubjects")
    private List<Student> enrolledStudents;
}
