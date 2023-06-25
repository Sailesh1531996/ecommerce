package com.ecommerce.cart.model.dao;

import com.ecommerce.cart.model.User;
import org.springframework.data.repository.ListCrudRepository;

/**
 * @author Sailesh
 * Data Access Object for the user data.
 */
public interface UserDAO extends ListCrudRepository<User,Long> {
    public User getUserByEmailAndName(String email,String name);
}
