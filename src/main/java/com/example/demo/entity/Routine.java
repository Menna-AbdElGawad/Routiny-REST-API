package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="routine")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Routine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name", nullable=false, unique=true)
    private String name;

    @Column(name="description")
    private String description;

    @Column(name="frequency")
    private int frequency;

    @ManyToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;

    @OneToMany(mappedBy = "routine")
    private List<Product> product = new ArrayList<>();
}
