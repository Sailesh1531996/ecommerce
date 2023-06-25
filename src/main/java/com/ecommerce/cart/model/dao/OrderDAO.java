package com.ecommerce.cart.model.dao;

import com.ecommerce.cart.model.Order;
import org.springframework.data.repository.ListCrudRepository;
public interface OrderDAO extends ListCrudRepository<Order,Long> {
}
