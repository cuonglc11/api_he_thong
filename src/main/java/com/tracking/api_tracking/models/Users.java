package com.tracking.api_tracking.models;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "username" , nullable = false , unique = true , length = 225)
    private String userName;
    @Column(name = "password_hash" , nullable = false , length = 225)
    private String password;
    @Column(name = "is_active")
    private int isActive = 1;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles", // Tên bảng trung gian
            joinColumns = @JoinColumn(name = "user_id"), // Khóa ngoại trỏ đến bảng users
            inverseJoinColumns = @JoinColumn(name = "role_id") // Khóa ngoại trỏ đến bảng role
    )
    private List<Roles> roles;

}
