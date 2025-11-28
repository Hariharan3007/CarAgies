package com.caragies.entitymodel;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Column(nullable = false, unique = true)
    private String username;

    private String password;

    @Email
    @Column(nullable = false, unique = true)
    private String email;

    private Long phone;

    private String role;

    @OneToMany(mappedBy = "users")
    private List<Car> car;



}
