package com.ecommerce.cart.service;

import com.ecommerce.cart.model.User;
import com.ecommerce.cart.model.dao.UserDAO;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public User saveUser(User user){

        return userDAO.save(user);
    }

    public Long isUserPresent(User user){
        User user1 = userDAO.getUserByEmailAndName(user.getEmail(), user.getName());
        return user1!=null ? user1.getId(): null ;
    }
}
