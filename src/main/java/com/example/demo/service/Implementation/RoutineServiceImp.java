package com.example.demo.service.Implementation;

import com.example.demo.dto.Request.RoutineRequest;
import com.example.demo.dto.Response.ProductResponse;
import com.example.demo.dto.Response.RoutineResponse;
import com.example.demo.entity.Product;
import com.example.demo.entity.Routine;
import com.example.demo.entity.User;
import com.example.demo.exception.DataNotExists;
import com.example.demo.repository.RoutineRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.Interface.RoutineService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoutineServiceImp implements RoutineService {

    private final RoutineRepository routineRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public RoutineResponse addRoutine(RoutineRequest routineRequest, String username) {
        User user = userRepository.findByUserName(username).orElseThrow(
                () -> new DataNotExists("User not found")
        );

        Routine routine = new Routine();
        routine.setName(routineRequest.getName());
        routine.setDescription(routineRequest.getDescription());
        routine.setFrequency(routineRequest.getFrequency());
        routine.setProfile(user.getProfile());

        Routine savedRoutine = routineRepository.save(routine);

        return new RoutineResponse(savedRoutine);
    }

    @Override
    @Transactional
    public RoutineResponse updateRoutine(Long routineId, RoutineRequest routineRequest, String username) {
        User user = userRepository.findByUserName(username).orElseThrow(
                () -> new DataNotExists("User not found")
        );

        Routine routine = routineRepository.findById(routineId).orElseThrow(
                () -> new DataNotExists("Routine not found")
        );

        if(!routine.getProfile().getId().equals(user.getProfile().getId())) {
            throw new DataNotExists("This routine does not belong to you");
        }

        if(routineRequest.getName() != null) {
            routine.setName(routineRequest.getName());
        }

        if(routineRequest.getDescription() != null) {
            routine.setDescription(routineRequest.getDescription());
        }

        if(routineRequest.getFrequency() != null) {
            routine.setFrequency(routineRequest.getFrequency());
        }

        Routine savedRoutine = routineRepository.save(routine);

        return new RoutineResponse(savedRoutine);
    }

    @Override
    @Transactional
    public void deleteRoutine(Long routineId, String username) {
        User user = userRepository.findByUserName(username).orElseThrow(
                () -> new DataNotExists("User not found")
        );

        Routine routine = routineRepository.findById(routineId).orElseThrow(
                () -> new DataNotExists("Routine not found")
        );

        if(!routine.getProfile().getId().equals(user.getProfile().getId())) {
            throw new DataNotExists("This routine does not belong to you");
        }

        routineRepository.delete(routine);
    }

    @Override
    @Transactional
    public List<ProductResponse> findProductsByRoutineId(Long routineId) {
        Routine routine = routineRepository.findById(routineId).orElseThrow(
                () -> new DataNotExists("Routine not found")
        );

        List<Product> products = routine.getProduct();
        List<ProductResponse> productResponses = new ArrayList<>();

        for(Product product : products) {
            productResponses.add(new ProductResponse(product));
        }

        return products.stream()
                .map(ProductResponse::new)
                .toList();

        /* == Another way ==
        *  =================
        *
        * return products.stream() --> to can make on the list operation Map
        *       .map(ProductResponse::new) --> for every product in list -> new ProductResponse(product) [Method Reference]
        *       .toList(); --> to return output in a list again
        * */
    }

}