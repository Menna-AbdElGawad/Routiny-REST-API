package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="profile")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="fullname")
    private String fullName;

    @Column(name="profilePicture")
    private String profilePicture;

    @OneToOne(mappedBy = "profile")
    private User user;

    @OneToMany(mappedBy = "profile")
    @JsonManagedReference
    private List<Routine> routine = new ArrayList<>();
}
