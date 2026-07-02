package com.tracking.api_tracking.models;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "timesheets")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Timesheets {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private Schedules schedules;
    @Column(name = "check_in_time" , nullable = false)
    private LocalDate checkInTime;
    @Column(name = "check_out_time" , nullable = false)
    private LocalDate checkOutTime;
    @Column(name = "actual_hours")
    private int actual =1;
    @Column(name = "status")
    private  int status = 1;
}
