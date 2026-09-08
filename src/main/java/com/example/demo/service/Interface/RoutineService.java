package com.example.demo.service.Interface;

import com.example.demo.dto.Request.RoutineRequest;
import com.example.demo.dto.Response.ProductResponse;
import com.example.demo.dto.Response.RoutineResponse;

import java.util.List;

public interface RoutineService {

    RoutineResponse addRoutine(RoutineRequest routineRequest, String username);

    RoutineResponse updateRoutine(Long routineId, RoutineRequest routineRequest, String username);

    void deleteRoutine(Long routineId, String username);

    List<ProductResponse> findProductsByRoutineId(Long routineId);

}
