package com.attendanceApp.entities;

import jakarta.persistence.*;
import lombok.*;


@Entity(name = "departments")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Setter
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, name = "department_name")
    private String name;

    @Column(nullable = false, unique = true)
    private String headOfDepartment;
}
