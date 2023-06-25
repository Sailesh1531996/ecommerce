package com.ecommerce.cart.api.model;

import com.ecommerce.cart.model.ShoppingCart;

import java.util.List;

public class ResponseBody {
    private double amount;
    private int invoiceNumber;
    private String date;
    private String OrderDescription;
    private Long orderId;
    public ResponseBody() {
    }

    public ResponseBody(double amount, int invoiceNumber, String date, String orderDescription, Long orderId) {
        this.amount = amount;
        this.invoiceNumber = invoiceNumber;
        this.date = date;
        OrderDescription = orderDescription;
        this.orderId = orderId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(int invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getOrderDescription() {
        return OrderDescription;
    }

    public void setOrderDescription(String orderDescription) {
        OrderDescription = orderDescription;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}
