package com.spring.attendanceApp.entities;

import com.spring.attendanceApp.enums.AttendanceStatus;
import jakarta.persistence.*;
import org.hibernate.annotations.CurrentTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "attendance_record")
public class AttendanceRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="student_id")
    private Student student;

    @Enumerated(EnumType.STRING)
    private AttendanceStatus status; // PRESENT, ABSENT, LATE

    @CurrentTimestamp
    @Column(name = "marked_at")
    private LocalDateTime markedAt;

    @ManyToOne
    @JoinColumn(name="session_id")
    private AttendanceSession session;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public AttendanceStatus getStatus() {
        return status;
    }

    public void setStatus(AttendanceStatus status) {
        this.status = status;
    }

    public LocalDateTime getMarkedAt() {
        return markedAt;
    }

    public void setMarkedAt(LocalDateTime markedAt) {
        this.markedAt = markedAt;
    }

    public AttendanceSession getSession() {
        return session;
    }

    public void setSession(AttendanceSession session) {
        this.session = session;
    }
}