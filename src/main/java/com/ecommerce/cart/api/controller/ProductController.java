package com.ecommerce.cart.api.controller;

import com.ecommerce.cart.api.exception.ProductAlreadyExistsException;
import com.ecommerce.cart.api.model.ProductBody;
import com.ecommerce.cart.model.Product;
import com.ecommerce.cart.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/insert")
    public ResponseEntity insertProduct(@RequestBody ProductBody productBody){
        try {
            productService.insertProduct(productBody);
            return ResponseEntity.ok().build();
        } catch (ProductAlreadyExistsException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @GetMapping(value = "/getAllProducts")
    public ResponseEntity<List<Product>> getAllProducts() {

        List<Product> productList = productService.getAllProducts();

        return ResponseEntity.ok(productList);
    }
}
