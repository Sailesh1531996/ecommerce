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

/**
 * @author Sailesh
 * Service for handling order actions.
 */
@Service
public class OrderService {

    /** The Order DAO. */
    private final OrderDAO orderDAO;

    /** The Product DAO. */
    private final ProductDAO productDAO;

    /** The shoppingCart DAO. */
    private ShoppingCartDAO shoppingCartDAO;

    /**
     * Parameterised Constructor of Order Service
     * @param orderDAO
     * @param productDAO
     * @param shoppingCartDAO
     */
    public OrderService(OrderDAO orderDAO, ProductDAO productDAO, ShoppingCartDAO shoppingCartDAO) {
        this.orderDAO = orderDAO;
        this.productDAO = productDAO;
        this.shoppingCartDAO =shoppingCartDAO;
    }

    /**
     * To get the order details
     * @param orderId
     * @return
     */
    public Order getOrderDetail(Long orderId) {
        Optional<Order> order = this.orderDAO.findById(orderId);
        return order.isPresent() ? order.get() : null;
    }


    /**
     * To the cart amount
     * @param shoppingCartList
     * @return
     */

    public void getCartAmount(List<ShoppingCart> shoppingCartList) {
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
                product1.setAvailableQuantity(availableQuantity);
                availableQuantity=0;
                cart.setProductName(product1.getName());
                cart.setAmount(singleCartAmount);
                productDAO.save(product1);
            }
        }
    }

    /**
     * To save the order in the order DAO
     * @param order
     * @return
     */

    public Order saveOrder(Order order) {
        return orderDAO.save(order);
    }

}
