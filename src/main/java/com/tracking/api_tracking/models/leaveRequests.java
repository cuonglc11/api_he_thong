package com.tracking.api_tracking.models;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "leave_requests")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class leaveRequests {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employees employees;
    @Column(name =  "start_date" , nullable = false)
    private LocalDate startDate;
    @Column(name =  "end_date" , nullable = false)
    private LocalDate endDate;
    @Column(name = "type")
    private int type = 1;
    @Column(name="reason")
    private String reason;
}
