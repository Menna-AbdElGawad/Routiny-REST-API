package com.example.demo.controller;

import com.example.demo.dto.Request.RoutineRequest;
import com.example.demo.dto.Response.ProductResponse;
import com.example.demo.dto.Response.RoutineResponse;
import com.example.demo.service.Interface.RoutineService;
import com.example.demo.service.Interface.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/routines")

public class RoutineController {
    private final RoutineService routineService;

    // POST /routines
    @PostMapping
    public ResponseEntity<RoutineResponse> addRoutine(
            @Valid @RequestBody RoutineRequest routineRequest,
            Authentication authentication) {

        return ResponseEntity.status(HttpStatus.CREATED).body(
                routineService.addRoutine(routineRequest, authentication.getName())
        );
    }

    // PUT /routines/{routineId}
    @PatchMapping("/{routineId}")
    public ResponseEntity<RoutineResponse> updateRoutine(
            @PathVariable Long routineId,
            @RequestBody RoutineRequest routineRequest,
            Authentication authentication) {

        return ResponseEntity.status(HttpStatus.OK).body(
                routineService.updateRoutine(routineId, routineRequest, authentication.getName())
        );
    }

    // DELETE /routines/{routineId}
    @DeleteMapping("/{routineId}")
    public void deleteRoutine(
            @PathVariable Long routineId, Authentication authentication) {

        routineService.deleteRoutine(routineId, authentication.getName());
    }

    // GET /routines/{routineId}/products
    @GetMapping("/{routineId}/products")
    public ResponseEntity<List<ProductResponse>> findProducts(@PathVariable Long routineId) {
        return ResponseEntity.status(HttpStatus.OK).body(
                routineService.findProductsByRoutineId(routineId)
        );
    }

}