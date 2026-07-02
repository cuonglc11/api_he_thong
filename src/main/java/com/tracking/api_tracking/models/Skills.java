package com.tracking.api_tracking.models;
import jakarta.persistence.*;
import lombok.*;
@Entity
@Table(name = "skills")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Skills {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name" , nullable = false , unique = true , length = 225)
    private String name;
    @Column(name = "is_certified")
    private int isCertified = 1;
}
