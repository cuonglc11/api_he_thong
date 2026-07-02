package com.tracking.api_tracking.models;
import jakarta.persistence.*;
import lombok.*;
@Entity
@Table(name = "employee_skills")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class employeeSkills {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employees employees;
    @ManyToOne
    @JoinColumn(name = "skill_id")
    private Skills skills;
    @Column(name = "level")
    private int level =1;
    @ManyToOne
    @JoinColumn(name = "verified_by")
    private Users users;
}
