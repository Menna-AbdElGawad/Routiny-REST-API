package com.example.demo.service.Implementation;

import com.example.demo.dto.Request.ProductRequest;
import com.example.demo.dto.Response.ProductResponse;
import com.example.demo.entity.Product;
import com.example.demo.entity.Routine;
import com.example.demo.entity.User;
import com.example.demo.exception.DataNotExists;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.RoutineRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.Interface.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImp implements ProductService {

    private final ProductRepository productRepository;
    private final RoutineRepository routineRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public ProductResponse addProduct(String userName, ProductRequest productRequest, Long routineId) {


        User user = userRepository.findByUserName(userName)
                .orElseThrow(() -> new DataNotExists("User not found"));


        Routine routine = routineRepository.findById(routineId)
                .orElseThrow(() -> new DataNotExists("Routine not found"));

        if(!routine.getProfile().getId().equals(user.getProfile().getId())) {
            throw new DataNotExists("This routine does not belong to you");
        }

        Product product = new Product();

        product.setName(productRequest.getName());
        product.setBrand(productRequest.getBrand());
        product.setDescription(productRequest.getDescription());
        product.setRoutine(routine);

        return new ProductResponse(productRepository.save(product));
    }

    @Override
    @Transactional
    public void deleteProduct(Long productId) {

        if(!productRepository.existsById(productId)) {
            throw new DataNotExists("Product not found");
        }

        productRepository.deleteById(productId);
    }

    @Override
    @Transactional
    public ProductResponse updateProduct(Long productId, ProductRequest productRequest, Long routineId) {

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new DataNotExists("Product Not Found"));
        
        if(productRequest.getName() != null) {
            product.setName(productRequest.getName());
        }

        if(productRequest.getBrand() != null) {
            product.setBrand(productRequest.getBrand());
        }

        if(productRequest.getDescription() != null) {
            product.setDescription(productRequest.getDescription());
        }

        Product updatedProduct = productRepository.save(product);

        return new ProductResponse(updatedProduct);
        
        
    }

    @Override
    public ProductResponse getProductById(Long productId) {

        Product product = productRepository.findById(productId).orElseThrow(
                () -> new DataNotExists("Product not found")
        );

        return new ProductResponse(product);
    }


    @Override
    @Transactional
    public List<ProductResponse> getProductsRoutine(Long routineId) {

        return productRepository.findByRoutineId(routineId);

    }
}
