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

/**
 * @author Sailesh
 * Controller to handle requests to place order,add an Item ,update the quantity of an item, delete an item from the cart  and view items in an order   .
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    /** The Order Service. */
    private OrderService orderService;

    /** The User Service. */
    private UserService userService ;

    /** The Shopping Cart Service. */
    private ShoppingCartService shoppingCartService ;

    /**
     * Parameterised Constructor
     * @param orderService
     * @param userService
     * @param shoppingCartService
     */

    public OrderController(OrderService orderService, UserService userService ,ShoppingCartService shoppingCartService) {
        this.orderService = orderService;
        this.userService = userService;
        this.shoppingCartService = shoppingCartService;
    }

    private Logger logger = LoggerFactory.getLogger(OrderController.class);

    /**
     * @param orderBody the order body that is passed form the body of the request.
     * @return the details of the order that is placed .
     */
    @PostMapping("/placeOrder")
    public ResponseEntity<Order> placeOrder(@RequestBody OrderBody orderBody) {
        logger.info("Request Payload " + orderBody.toString());
        ResponseBody responseBody = new ResponseBody();
        orderService.getCartAmount(orderBody.getCartItems());

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
        return ResponseEntity.ok(order);
    }


    /**
     * Method to get the order or cart details
     * @param orderId order id of the order
     * @return return the details of the items in the cart and the user details that has placed the order .
     */

    @GetMapping(value = "/getOrder/{orderId}")
    public ResponseEntity<Order> getOrderDetails(@PathVariable Long orderId) {
        Order order = orderService.getOrderDetail(orderId);
        return ResponseEntity.ok(order);
    }

    /**
     * To add item in to the cart
     * @param cartItemBody the cart item body that is passed form the body of the request .
     * @return returns the added cart list
     */
    @PostMapping("/addItem")
    public ResponseEntity<Order> addItem(@RequestBody CartItemBody cartItemBody) {
        logger.info("Request Payload " + cartItemBody.toString());
        shoppingCartService.addItem(cartItemBody);
        Order order = orderService.getOrderDetail(cartItemBody.getOrderId());
        return ResponseEntity.ok(order);

    }

    /**
     * Delete an item from the cart according to the id .
     * @param itemId id of the item that has to be deleted .
     * @return returns a success message  that an item is deleted .
     */
    @DeleteMapping("deleteItem/{id}")
    public ResponseEntity<String> deleteCartItem(@PathVariable("id") Long itemId) {
        shoppingCartService.deleteItem(itemId);
        return ResponseEntity.ok("Cart item deleted");
    }

    /**
     * To update the quantity of the product .
     * @param itemId
     * @param columnValue new quantity value
     * @return returns a success message  that item quantity is updated .
     */
    @PatchMapping("update/{id}")
    public ResponseEntity<String> updateCartItemQuantity(
            @PathVariable("id") Long itemId,
            @RequestParam("value") int columnValue
    ) {
        shoppingCartService.updateCartItemQuantity(itemId, columnValue);
        return ResponseEntity.ok("Cart item quantity Updated");
    }
}
