package com.attendanceApp.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class Students {

    @Id
    private Long Id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private Users user;

    @ManyToOne
    @JoinColumn(name = "current_class_id")
    private AcademicClasses currentClassId;

    @Column(nullable = false, unique = true)
    private String rollNumber;

    @Column(nullable = false)
    private String fatherName;
}
