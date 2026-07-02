package com.tracking.api_tracking.models;
import jakarta.persistence.*;
import lombok.*;
@Entity
@Table(name = "employees")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Employees {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users users;
    @ManyToOne
    @JoinColumn(name = "branch_id")
    private Branches branches;
    @Column(name = "employee_code" , nullable = false , unique = true , length = 225)
    private String code;
    @Column(name = "full_name" , nullable = false , length = 225)
    private String fullName;
    @Column(name = "contract_type")
    private int  contractType = 1;
    @Column(name = "phone" , nullable = false , unique = true , length = 225)
    private String phone;
    @Column(name = "email" , nullable = false , unique = true , length = 225)
    private String email;
    @Column(name = "max_weekly_hrs")
    private int maxWeklyHrs =1;
    @Column(name = "leave_quota")
    private int leaveQuota = 1;
    @Column(name = "status")
    private int status = 1;
}
