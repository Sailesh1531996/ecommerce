package com.ecommerce.cart.model.dao;

import com.ecommerce.cart.model.Product;

import org.springframework.data.repository.ListCrudRepository;

public interface ProductDAO extends ListCrudRepository<Product,Long> {
}
