package com.ecommerce.cart.service;

import com.ecommerce.cart.api.model.CartItemBody;
import com.ecommerce.cart.model.Order;
import com.ecommerce.cart.model.Product;
import com.ecommerce.cart.model.ShoppingCart;
import com.ecommerce.cart.model.dao.OrderDAO;
import com.ecommerce.cart.model.dao.ProductDAO;
import com.ecommerce.cart.model.dao.ShoppingCartDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShoppingCartService {
    private final ShoppingCartDAO shoppingCartDAO;
    private final ProductDAO productDAO;

    private final OrderDAO orderDAO ;
    private final Logger logger = LoggerFactory.getLogger(ShoppingCartService.class);

    public ShoppingCartService(ShoppingCartDAO shoppingCartDAO, ProductDAO productDAO, OrderDAO orderDAO) {
        this.shoppingCartDAO = shoppingCartDAO;
        this.productDAO = productDAO;
        this.orderDAO = orderDAO;
    }

    public void deleteItem(Long itemId) {
        Optional<ShoppingCart> item = shoppingCartDAO.findById(itemId);
        if(item.isPresent()){
            ShoppingCart cart = item.get();
            Optional<Product> productList = productDAO.findById(cart.getProductId());
            if(productList.isPresent()) {
                Product product = productList.get();
                product.setAvailableQuantity(product.getAvailableQuantity()+ cart.getQuantity());
                productDAO.save(product);
            }
            shoppingCartDAO.deleteById(itemId);
        }
        else {
            logger.info("No Item in the cart");
            throw new IllegalArgumentException("No Item in the cart");
        }
    }

    public void updateCartItemQuantity(Long itemId, int columnValue) {
        int actualQuantity = 0;
        double singleCartAmount = 0.0;
        int availableQuantity = 0;
        Optional<ShoppingCart> existingItem = shoppingCartDAO.findById(itemId);
        ShoppingCart shoppingCart;

        if(existingItem.isPresent()) {
            shoppingCart = existingItem.get();
            actualQuantity = shoppingCart.getQuantity();
            shoppingCart.setQuantity(columnValue);
            Long productId = shoppingCart.getProductId();
            Optional<Product> product = productDAO.findById(productId);
            if (product.isPresent()) {
                Product product1 = product.get();
                if (product1.getAvailableQuantity() < (columnValue-actualQuantity)) {
                    singleCartAmount = product1.getPrice() * product1.getAvailableQuantity();
                    shoppingCart.setQuantity(product1.getAvailableQuantity()
                            + actualQuantity);
                } else {
                    singleCartAmount = shoppingCart.getQuantity() * product1.getPrice();
                    availableQuantity = product1.getAvailableQuantity() - shoppingCart.getQuantity()+actualQuantity;
                }
                product1.setAvailableQuantity(availableQuantity);
                shoppingCart.setAmount(singleCartAmount);
                productDAO.save(product1);
            }
        }
        else {
            logger.info("No Item in the cart");
            throw new IllegalArgumentException("No Item in the cart");
        }

        shoppingCartDAO.save(shoppingCart);
    }

    public void addItem(CartItemBody cartItemBody) {
        double singleCartAmount = 0;
        int availableQuantity = 0;
        Optional<Order> orderItem = orderDAO.findById(cartItemBody.getOrderId());
        if (orderItem.isPresent()) {
            Order order = orderItem.get();
            List<ShoppingCart> cartItems = order.getCartItems();
            ShoppingCart cart = new ShoppingCart();
            cart.setProductId(cartItemBody.getProductId());
            cart.setQuantity(cartItemBody.getQuantity());
            Optional<Product> product = productDAO.findById(cartItemBody.getProductId());
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
                cart.setProductName(product1.getName());
                cart.setAmount(singleCartAmount);
                productDAO.save(product1);
                cart.setQuantity(cartItemBody.getQuantity());
                cartItems.add(cart);
                order.setCartItems(cartItems);
                orderDAO.save(order);
            }
        }else {
                logger.info("No Item in the cart");
                throw new IllegalArgumentException("No Item in the cart");
            }

        }
    }