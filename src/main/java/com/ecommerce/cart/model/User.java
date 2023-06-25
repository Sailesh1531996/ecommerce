package com.ecommerce.cart.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


/**
 * @author Sailesh
 * A class for User.
 */
@Entity
@Table(name = "local_user")
public class User {

    /**
     * default constructor for the order.
     */
    public User() {

    }

    /**
     * parameterised constructor for the order.
     */
    public User(String email, String name) {
        this.email = email;
        this.name = name;
    }


    /** Unique id for the user. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    /** The  email of the user. */
    @Column(name = "email", nullable = false, unique = true, length = 320)
    private String email;

    /** The  name of the user. */
    @Column(name = "first_name", nullable = false)
    private String name;


    /**
     * @return return the id of the cart
     */
    public Long getId() {
        return id;
    }


    /**
     * @param id set the id to the cart
     */
    public void setId(Long id) {
        this.id = id;
    }


    /**
     * @return return the name of the user
     */
    public String getName() {
        return name;
    }


    /**
     * @param name set the name to the user
     */
    public void setName(String name) {
        this.name = name;
    }


    /**
     * @return return the email of the user
     */

    public String getEmail() {
        return email;
    }

    /**
     * @param email set the email to the user
     */
    public void setEmail(String email) {
        this.email = email;
    }


}