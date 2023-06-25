package com.ecommerce.cart.api.model;

import com.ecommerce.cart.model.ShoppingCart;

import java.util.List;

public class OrderBody {
    private String orderDescription;
    private List<ShoppingCart> cartItems;
    private String userEmail;
    private String userName;
    public OrderBody() {
    }

    public OrderBody(String orderDescription, List<ShoppingCart> cartItems, String userEmail, String userName) {
        this.orderDescription = orderDescription;
        this.cartItems = cartItems;
        this.userEmail = userEmail;
        this.userName = userName;
    }

    public String getOrderDescription() {
        return orderDescription;
    }

    public void setOrderDescription(String orderDescription) {
        this.orderDescription = orderDescription;
    }

    public List<ShoppingCart> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<ShoppingCart> cartItems) {
        this.cartItems = cartItems;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
