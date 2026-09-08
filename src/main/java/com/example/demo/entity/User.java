package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name="user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="username", unique=true)
    private String userName;

    @Column(name="password")
    @Size(min = 8)
    private String password;

    @Email
    @Column(name="email")
    private String email;

    @OneToOne(cascade=CascadeType.REMOVE)
    @JoinColumn(name = "profile_id")
    @JsonBackReference
    private Profile profile;
}
