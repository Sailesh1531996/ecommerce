package com.ecommerce.cart.api.controller;

import com.ecommerce.cart.api.model.CartItemBody;
import com.ecommerce.cart.api.model.OrderBody;
import com.ecommerce.cart.api.model.ResponseBody;
import com.ecommerce.cart.model.Order;
import com.ecommerce.cart.model.User;
import com.ecommerce.cart.service.OrderService;
import com.ecommerce.cart.service.ShoppingCartService;
import com.ecommerce.cart.service.UserService;
import com.ecommerce.cart.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Random;
@RestController
@RequestMapping("/order")
public class OrderController {
    private OrderService orderService;
    private UserService userService ;

    private ShoppingCartService shoppingCartService ;

    public OrderController(OrderService orderService, UserService userService ,ShoppingCartService shoppingCartService) {
        this.orderService = orderService;
        this.userService = userService;
        this.shoppingCartService = shoppingCartService;
    }

    private Logger logger = LoggerFactory.getLogger(OrderController.class);

    @PostMapping("/placeOrder")
    public ResponseEntity<ResponseBody> placeOrder(@RequestBody OrderBody orderBody) {
        logger.info("Request Payload " + orderBody.toString());
        ResponseBody responseBody = new ResponseBody();
        double amount = orderService.getCartAmount(orderBody.getCartItems());

        User user = new User(orderBody.getUserName(), orderBody.getUserEmail()  );
        Long userIdFromDb = userService.isUserPresent(user);
        if (userIdFromDb != null) {
            user.setId(userIdFromDb);
            logger.info("Customer already present in db with id : " + userIdFromDb);
        }else{
            user = userService.saveUser(user);
            logger.info("Customer saved.. with id : " + user.getId());
        }
        Order order = new Order(orderBody.getOrderDescription(), user, orderBody.getCartItems());
        order = orderService.saveOrder(order);
        logger.info("Order processed successfully..");
        responseBody.setAmount(amount);
        responseBody.setDate(DateUtil.getCurrentDateTime());
        responseBody.setInvoiceNumber(new Random().nextInt(1000));
        responseBody.setOrderId(order.getId());
        responseBody.setOrderDescription(orderBody.getOrderDescription());
        logger.info("test push..");
        return ResponseEntity.ok(responseBody);
    }

    @GetMapping(value = "/getOrder/{orderId}")
    public ResponseEntity<Order> getOrderDetails(@PathVariable Long orderId) {
        Order order = orderService.getOrderDetail(orderId);
        return ResponseEntity.ok(order);
    }
    @PostMapping("/addItem")
    public ResponseEntity<Order> addItem(@RequestBody CartItemBody cartItemBody) {
        logger.info("Request Payload " + cartItemBody.toString());
        shoppingCartService.addItem(cartItemBody);
        Order order = orderService.getOrderDetail(cartItemBody.getOrderId());
        return ResponseEntity.ok(order);

    }

    @DeleteMapping("deleteItem/{id}")
    public ResponseEntity<String> deleteCartItem(@PathVariable("id") Long itemId) {
        shoppingCartService.deleteItem(itemId);
        return ResponseEntity.ok("Cart item deleted");
    }

    @PatchMapping("update/{id}")
    public ResponseEntity<String> updateCartItemQuantity(
            @PathVariable("id") Long itemId,
            @RequestParam("value") int columnValue
    ) {
        shoppingCartService.updateCartItemQuantity(itemId, columnValue);
        return ResponseEntity.ok("Cart item quantity Updated");
    }
}
