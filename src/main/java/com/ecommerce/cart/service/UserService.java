package com.ecommerce.cart.service;

import com.ecommerce.cart.model.User;
import com.ecommerce.cart.model.dao.UserDAO;
import org.springframework.stereotype.Service;
/**
 * @author Sailesh
 * Service for handling user actions.
 */
@Service
public class UserService {
    /** The user DAO */
    private UserDAO userDAO;

    /**
     * Parameterised constructor of the user service .
     * @param userDAO
     */
    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    /**
     * Save the user in the user DAO
     * @param user
     * @return
     */
    public User saveUser(User user){

        return userDAO.save(user);
    }

    /**
     *To check whether the user is already present in the DAO
     * @param user
     * @return
     */
    public Long isUserPresent(User user){
        User user1 = userDAO.getUserByEmailAndName(user.getEmail(), user.getName());
        return user1!=null ? user1.getId(): null ;
    }
}
