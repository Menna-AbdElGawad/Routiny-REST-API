package com.example.demo.repository;

import com.example.demo.entity.Routine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoutineRepository extends JpaRepository<Routine, Long> {

    Optional<Routine> findById(Long routineId);
}
