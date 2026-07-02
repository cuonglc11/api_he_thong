package com.tracking.api_tracking.models;
import jakarta.persistence.*;
import lombok.*;
@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "code" , nullable = false , unique = true , length = 225)
    private String code;
}
