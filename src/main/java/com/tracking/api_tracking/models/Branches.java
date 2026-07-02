package com.tracking.api_tracking.models;

import jakarta.persistence.*;
import lombok.*;
@Entity
@Table(name = "branches")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Branches {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name" , nullable = false , unique = true , length = 225)
    private String name;
    @Column(name = "location" , nullable = false , length = 225)
    private String location;
    @Column(name = "timezone" , nullable = false , length = 225)
    private String timezone;
    @Column(name = "code" , nullable = false , unique = true , length = 225)
    private String code;
    @Column(name = "phone" , nullable = false , unique = true , length = 225)
    private String phone;
    @Column(name = "email" , nullable = false , unique = true , length = 225)
    private String email;
    @Column(name = "is_active")
    private int isActive=1;
    @Column(name = "gps_radius")
    private int gpsRadius = 1;




}
