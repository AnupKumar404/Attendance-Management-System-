package com.spring.attendanceApp.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "teacher")
@Data
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AttendanceSession> session = new ArrayList<>();

    @OneToMany(mappedBy = "teacher")
    private List<Subject> subjects = new ArrayList<>();
}
