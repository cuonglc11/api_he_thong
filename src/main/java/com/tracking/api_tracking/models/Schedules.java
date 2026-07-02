package com.tracking.api_tracking.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "schedules")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Schedules {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employees employees;
    @ManyToOne
    @JoinColumn(name = "shift_id")
    private Shifts shifts;
    @Column(name = "date" , nullable = false)
    private LocalDate date;
    @Column(name = "status")
    private int status = 1;
}
