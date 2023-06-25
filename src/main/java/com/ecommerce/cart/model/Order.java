package com.ecommerce.cart.model;

import jakarta.persistence.*;
import java.util.List;


/**
 * @author Sailesh
 * A class for order of the product.
 */
@Entity
@Table (name = "shop_order")
public class Order {

    /** Unique id for the product. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * Description of the order
     */
    private String orderDescription;

    /**
     * The user who has ordered .
     */
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private User user;

    /**
     * Items in the cart .
     */
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, targetEntity = ShoppingCart.class)
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private List<ShoppingCart> cartItems;

    /**
     * default constructor for the order.
     */
    public Order() {
    }

    /**
     * parameterised constructor for the order.
     */
    public Order(String orderDescription, User customer, List<ShoppingCart> cartItems) {
        this.orderDescription = orderDescription;
        this.user = customer;
        this.cartItems = cartItems;
    }

    /**
     *
     * @return return the id of the order .
     */
    public Long getId() {
        return id;
    }

    /**
     *
     * @param id sets the id of the order .
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     *
     * @return return the order description of the order .
     */

    public String getOrderDescription() {
        return orderDescription;
    }

    /**
     *
     * @param orderDescription sets the description to the order .
     */
    public void setOrderDescription(String orderDescription) {
        this.orderDescription = orderDescription;
    }

    /**
     *
     * @return return the user who has done the order .
     */
    public User getCustomer() {
        return user;
    }

    /**
     *
     * @param customer sets the user to the order .
     */
    public void setCustomer(User customer) {
        this.user = customer;
    }

    /**
     *
     * @return return the items in the cart .
     */
    public List<ShoppingCart> getCartItems() {
        return cartItems;
    }

    /**
     *
     * @param cartItems set the items to the cart
     */

    public void setCartItems(List<ShoppingCart> cartItems) {
        this.cartItems = cartItems;
    }
}