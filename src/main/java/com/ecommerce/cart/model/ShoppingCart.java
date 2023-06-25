package com.ecommerce.cart.model;


import jakarta.persistence.*;

/**
 * @author Sailesh
 * A class for cart.
 */
@Entity
@Table(name = "shop_cart_10")
public class ShoppingCart {

    /** Unique id for the cart. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    /** Product id of the item in the cart. */
    @Column(name = "productId", nullable = false, length = 320)
    private Long productId;

    /** The  name of the product. */
    @Column(name = "productName", nullable = false)
    private String productName;

    /** The  quantity of the product. */
    @Column(name = "quantity", nullable = false,length = 320)
    private int quantity;

    /** Total amount of the single item according to the quantity. */
    @Column(name = "amount", nullable = false,length = 320)
    private double amount;


    /**
    Default Constructor of the ShoppingCart
     */
    public ShoppingCart() {
    }

    /**
     Parameterised Constructor of the ShoppingCart
     */
    public ShoppingCart(Long productId, String productName, int quantity, float amount) {
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.amount = amount;
    }

    /**
     *Two Parameterised Constructor of the ShoppingCart
     */
    public ShoppingCart(Long productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    /**
     *
     * @return return the id of the cart
     */
    public Long getId() {
        return id;
    }

    /**
     *
     * @param id set the id to the cart
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     *
     * @return return the product id of the item in the cart
     */
    public Long getProductId() {
        return productId;
    }

    /**
     *
     * @param productId set the product id of the item in the cart
     */
    public void setProductId(Long productId) {
        this.productId = productId;
    }

    /**
     *
     * @return return the name of the item in the cart
     */
    public String getProductName() {
        return productName;
    }

    /**
     * @param productName set the product name in the cart
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * @return return the quantity of the product
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param quantity set the quantity of the product
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * @return return the amount of the product
     */
    public double getAmount() {
        return amount;
    }

    /**
     * @param amount sets the amount of the product
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "id=" + id +
                ", productId=" + productId +
                ", productName='" + productName + '\'' +
                ", quantity=" + quantity +
                ", amount=" + amount +
                '}';
    }
}