package com.ecommerce.cart.service;

import com.ecommerce.cart.model.Order;
import com.ecommerce.cart.model.Product;
import com.ecommerce.cart.model.ShoppingCart;
import com.ecommerce.cart.model.dao.OrderDAO;
import com.ecommerce.cart.model.dao.ProductDAO;
import com.ecommerce.cart.model.dao.ShoppingCartDAO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private final OrderDAO orderDAO;
    private final ProductDAO productDAO;

    private ShoppingCartDAO shoppingCartDAO;
    public OrderService(OrderDAO orderDAO, ProductDAO productDAO, ShoppingCartDAO shoppingCartDAO) {
        this.orderDAO = orderDAO;
        this.productDAO = productDAO;
        this.shoppingCartDAO =shoppingCartDAO;
    }

    public Order getOrderDetail(Long orderId) {
        Optional<Order> order = this.orderDAO.findById(orderId);
        return order.isPresent() ? order.get() : null;
    }



    public double getCartAmount(List<ShoppingCart> shoppingCartList) {

        double totalCartAmount = 0;
        double singleCartAmount = 0;
        int availableQuantity = 0;

        for (ShoppingCart cart : shoppingCartList) {

            Long productId = cart.getProductId();
            Optional<Product> product = productDAO.findById(productId);
            if (product.isPresent()) {
                Product product1 = product.get();
                if (product1.getAvailableQuantity() < cart.getQuantity()) {
                    singleCartAmount = product1.getPrice() * product1.getAvailableQuantity();
                    cart.setQuantity(product1.getAvailableQuantity());
                } else {
                    singleCartAmount = cart.getQuantity() * product1.getPrice();
                    availableQuantity = product1.getAvailableQuantity() - cart.getQuantity();
                }
                totalCartAmount = totalCartAmount + singleCartAmount;
                product1.setAvailableQuantity(availableQuantity);
                availableQuantity=0;
                cart.setProductName(product1.getName());
                cart.setAmount(singleCartAmount);
                productDAO.save(product1);
            }
        }
        return totalCartAmount;
    }

    public Order saveOrder(Order order) {
        return orderDAO.save(order);
    }

}
