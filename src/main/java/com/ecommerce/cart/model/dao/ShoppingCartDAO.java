package com.ecommerce.cart.model.dao;

import com.ecommerce.cart.model.ShoppingCart;

import org.springframework.data.repository.ListCrudRepository;
/**
 * @author Sailesh
 * Data Access Object for the Shopping cart data.
 */
public interface ShoppingCartDAO extends ListCrudRepository<ShoppingCart,Long> {

}
