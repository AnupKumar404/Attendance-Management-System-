package com.attendanceApp.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class AcademicClasses {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false, unique = true)
    private Departments department;

    @Column(nullable = false, unique = true)
    private Integer semester;

    @Column(nullable = false, unique = true)
    private String section;

    @Column(nullable = false, unique = true)
    private Integer batchYear;
}
