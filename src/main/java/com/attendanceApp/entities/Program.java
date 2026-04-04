package com.attendanceApp.entities;

import com.attendanceApp.enums.ProgramType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(
                name = "unique_department_program",
                columnNames = {"departmentId", "id"}
        )
}
)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Program {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "program_name")
    private ProgramType name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    @Column(name = "duration_of_program", nullable = false)
    private Integer duration;

    @Column(name = "total_semesters", nullable = false)
    private Integer totalSemesters;
}
