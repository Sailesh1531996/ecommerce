package com.ecommerce.cart.model.dao;

import com.ecommerce.cart.model.ShoppingCart;

import org.springframework.data.repository.ListCrudRepository;

public interface ShoppingCartDAO extends ListCrudRepository<ShoppingCart,Long> {

}
