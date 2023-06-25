package com.ecommerce.cart.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * @author Sailesh
 * A product available for purchasing.
 */
@Entity
@Table(name = "product")
public class Product {

    public Product() {
    }
    public Product(Long id, String name, Double price, int availableQuantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.availableQuantity = availableQuantity;
    }

    /** Unique id for the product. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    /** The name of the product. */
    @Column(name = "name", nullable = false, unique = true)
    private String name;



    /** The price of the product. */

    @Column(name = "price", nullable = false)
    private Double price;

    /** The available quantity  of the product. */
    @Column(name = "availableQuantity", nullable = false)
    private int availableQuantity;


    /**
     *
     * @return return the price of the product .
     */
    public Double getPrice() {
        return price;
    }

    /**
     * @param price Sets the price of the product.
     */
    public void setPrice(Double price) {
        this.price = price;
    }


    /**
     * @return return the name of the product.
     */
    public String getName() {
        return name;
    }

    /**
     * @param name sets the name of the product.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return return the id of the product.
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id set the id of the product.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return return the available quantity of the product.
     */
    public int getAvailableQuantity() {
        return availableQuantity;
    }

    /**
     * @param availableQuantity set the available quantity of the product .
     */
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