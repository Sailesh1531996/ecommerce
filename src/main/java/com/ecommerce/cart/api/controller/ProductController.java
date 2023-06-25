package com.ecommerce.cart.api.controller;

import com.ecommerce.cart.api.model.ProductBody;
import com.ecommerce.cart.model.Product;
import com.ecommerce.cart.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Sailesh
 * Controller to handle requests to insert the products in their table , to show all products .
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    /** The Product Service. */
    private ProductService productService;

    /**
     * Constructor
     * @param productService
     */
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * To insert the products into their table
     * @param productBody the Product body that is passed form the body of the request .
     * @return returns ok after inserting into the table
     */
    @PostMapping("/insert")
    public ResponseEntity insertProduct(@RequestBody ProductBody productBody){
        try {
            productService.insertProduct(productBody);
            return ResponseEntity.ok().build();
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    /**
     *To get all the product details that are available to purchase .
     * @return returns all products .
     */
    @GetMapping(value = "/getAllProducts")
    public ResponseEntity<List<Product>> getAllProducts() {

        List<Product> productList = productService.getAllProducts();

        return ResponseEntity.ok(productList);
    }
}
