package com.tracking.api_tracking.models;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "branch_managers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BranchManagers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employees employees;
    @ManyToOne
    @JoinColumn(name = "branch_id")
    private Branches branches;
    @Column(name =  "start_date" , nullable = false)
    private LocalDate startDate;
    @Column(name =  "end_date" , nullable = false)
    private LocalDate endDate;
    @ManyToOne
    @JoinColumn(name = "verified_by")
    private Users users;
}
