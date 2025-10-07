package com.spring.attendanceApp.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.spring.attendanceApp.enums.AttendanceStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CurrentTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "attendance_record")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttendanceRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private AttendanceStatus status; // PRESENT, ABSENT, LATE

    @CurrentTimestamp
    @Column(name = "marked_at")
    private LocalDateTime markedAt;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="student_id")
    private Student student;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attendance_session_id")
    private AttendanceSession session;
}