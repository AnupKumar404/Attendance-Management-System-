package com.attendanceApp.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity(name = "students")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Student {

    @Id
    private Long Id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "current_class_id")
    private AcademicClass currentClassId;

    @Column(nullable = false, unique = true)
    private String rollNumber;

    @Column(nullable = false)
    private String fatherName;

    @ManyToMany
    @JoinTable(name = "Enrollment",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id")
    )
    private List<Subject> enrolledSubjects;
}
