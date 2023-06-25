package com.ecommerce.cart.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * A product available for purchasing.
 */
@Entity
@Table(name = "product")
public class Product {

    /** Unique id for the product. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    /** The name of the product. */
    @Column(name = "name", nullable = false, unique = true)
    private String name;



    /** The short description of the product. */

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "availableQuantity", nullable = false)
    private int availableQuantity;

    public Double getPrice() {
        return price;
    }

    /**
     * Sets the price of the product.
     * @param price The price.
     */
    public void setPrice(Double price) {
        this.price = price;
    }


    /**
     * Gets the name of the product.
     * @return The name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the product.
     * @param name The name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the id of the product.
     * @return The id.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the id of the product.
     * @param id The id.
     */
    public void setId(Long id) {
        this.id = id;
    }

    public int getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(int availableQuantity) {
        this.availableQuantity = availableQuantity;
    }


    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", availableQuantity=" + availableQuantity +
                ", price=" + price +
                '}';
    }

}