package com.ecommerce.cart.model.dao;

import com.ecommerce.cart.model.Product;

import org.springframework.data.repository.ListCrudRepository;
/**
 * @author Sailesh
 * Data Access Object for the product data.
 */
public interface ProductDAO extends ListCrudRepository<Product,Long> {
}
