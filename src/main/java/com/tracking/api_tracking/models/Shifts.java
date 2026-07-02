package com.tracking.api_tracking.models;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "shifts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Shifts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "branch_id")
    private Branches branches;
    @ManyToOne
    @JoinColumn(name = "req_skill_id")
    private Skills skills;
    @Column(name =  "start_date" , nullable = false)
    private LocalDate startDate;
    @Column(name =  "end_date" , nullable = false)
    private LocalDate endDate;

}
