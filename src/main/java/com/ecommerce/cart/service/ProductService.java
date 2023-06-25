package com.ecommerce.cart.service;


import com.ecommerce.cart.api.model.ProductBody;
import com.ecommerce.cart.model.Product;
import com.ecommerce.cart.model.dao.ProductDAO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Sailesh
 * Service for handling product actions.
 */
@Service
public class ProductService {


    /** The Order DAO. */
    private ProductDAO productDAO ;

    /**
     *Parameterised Constructor
     * @param productDAO
     */
    public ProductService(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    /**
     * To add products in to the table .
     * @param productBody
     * @return
     */
    public Product insertProduct(ProductBody productBody){
        Product product = new Product();
        product.setName(productBody.getName());
        product.setPrice(productBody.getPrice());
        product.setAvailableQuantity(productBody.getAvailableQuantity());
        return productDAO.save(product);
    }

    /**
     * To get all products that are available to purchase.
     * @return
     */
    public List<Product> getAllProducts() {
        return productDAO.findAll();
    }
}
