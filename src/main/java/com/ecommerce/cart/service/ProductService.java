package com.ecommerce.cart.service;


import com.ecommerce.cart.api.exception.ProductAlreadyExistsException;
import com.ecommerce.cart.api.model.ProductBody;
import com.ecommerce.cart.model.Product;
import com.ecommerce.cart.model.dao.ProductDAO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private ProductDAO productDAO ;

    public ProductService(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    public Product insertProduct(ProductBody productBody)throws ProductAlreadyExistsException {
        Product product = new Product();
        product.setName(productBody.getName());
        product.setPrice(productBody.getPrice());
        product.setAvailableQuantity(productBody.getAvailableQuantity());
        return productDAO.save(product);
    }


    public List<Product> getAllProducts() {
        return productDAO.findAll();
    }
}
