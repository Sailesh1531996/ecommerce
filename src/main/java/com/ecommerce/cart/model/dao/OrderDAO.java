package com.ecommerce.cart.model.dao;

import com.ecommerce.cart.model.Order;
import org.springframework.data.repository.ListCrudRepository;

/**
 * @author Sailesh
 * Data Access Object for the order data.
 */
public interface OrderDAO extends ListCrudRepository<Order,Long> {
}
