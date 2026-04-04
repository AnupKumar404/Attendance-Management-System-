package com.attendanceApp.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "academic_classes")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AcademicClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false, unique = true)
    private Department department;

    @Column(nullable = false, unique = true)
    private Integer semester;

    @Column(nullable = false, unique = true)
    private String section;

    @Column(nullable = false, unique = true)
    private Integer batchYear;
}
