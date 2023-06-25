package com.ecommerce.cart;

import com.ecommerce.cart.api.model.CartItemBody;
import com.ecommerce.cart.api.model.ProductBody;
import com.ecommerce.cart.model.Order;
import com.ecommerce.cart.model.Product;
import com.ecommerce.cart.model.ShoppingCart;
import com.ecommerce.cart.model.User;
import com.ecommerce.cart.model.dao.OrderDAO;
import com.ecommerce.cart.model.dao.ProductDAO;
import com.ecommerce.cart.model.dao.ShoppingCartDAO;
import com.ecommerce.cart.model.dao.UserDAO;
import com.ecommerce.cart.service.OrderService;
import com.ecommerce.cart.service.ProductService;
import com.ecommerce.cart.service.ShoppingCartService;
import com.ecommerce.cart.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.junit.Assert.assertEquals;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
class CartApplicationTests {
	@Autowired
	private OrderService orderService;

	@Autowired
	private UserService userService;

	@Autowired
	private ShoppingCartService shoppingCartService;

	@Autowired
	private ProductService productService;
	@MockBean
	private OrderDAO orderDAO;
	@MockBean
	private ProductDAO productDAO;

	@MockBean
	private UserDAO userDAO;

	@MockBean
	private ShoppingCart cart;
	@MockBean
	private Order order;
	@MockBean
	private User user;

	@MockBean
	private Product product;

	@MockBean
	private ShoppingCartDAO shoppingCartDAO;

	@Test
	public void getOrderDetailTest() {
		Long id = 0L;
		List<ShoppingCart> cartItems = new ArrayList<>();
		when(orderDAO.findById(id)).thenReturn(Optional.ofNullable(order));
		assertEquals(order, orderService.getOrderDetail(id));
	}

	@Test
	public void getCartAmount() {
		Long id = 0L;
		Long number = 500L;
		List<ShoppingCart> shoppingCartList = new ArrayList<>();
		ShoppingCart cart = new ShoppingCart(id, 5);
		shoppingCartList.add(cart);
		when(productDAO.findById(id)).thenReturn(Optional.of(product));
		orderService.getCartAmount(shoppingCartList);
		verify(productDAO, times(1)).save(product);
	}

	@Test
	public void saveOrder() {
		when(orderDAO.save(order)).thenReturn(order);
		assertEquals(order, orderService.saveOrder(order));
	}


	@Test
	public void saveUser() {
		when(userDAO.save(user)).thenReturn(user);
		assertEquals(user, userService.saveUser(user));
	}

	@Test
	public void isUserPresent() {
		Long id = 0L;
		when(userDAO.getUserByEmailAndName(user.getEmail(), user.getName())).thenReturn(user);
		assertEquals(id, userService.isUserPresent(user));
	}
	@Test
	public void insertProduct() {
		Product product = new Product();
		product.setName("name");
		product.setPrice(1000.0);
		product.setAvailableQuantity(100);
		ProductBody productBody = new ProductBody();
		productBody.setName("name");
		productBody.setPrice(1000.0);
		productBody.setAvailableQuantity(100);
		when(productDAO.save(product)).thenReturn(product);
		assertEquals(null,productService.insertProduct(productBody));
	}


	@Test
	public void getAllProducts() {
		Long id =1L ;
		when(productDAO.findAll()).thenReturn(Stream
				.of(new Product(id, "name", 410.0,5)).collect(Collectors.toList()));
		assertEquals(1, productService.getAllProducts().size());
	}

	@Test
	public void deleteItem() {
		Long id = 1L;
		ShoppingCart item = new ShoppingCart();
		item.setProductId(id);
		when(shoppingCartDAO.findById(id)).thenReturn(Optional.of(item));
		when(productDAO.findById(id)).thenReturn(Optional.of(product));
		shoppingCartService.deleteItem(id);
		verify(shoppingCartDAO, times(1)).deleteById(id);
	}


	@Test
	public void updateCartItemQuantity() {
		Long id = 1L;
		ShoppingCart item = new ShoppingCart();
		item.setProductId(id);
		when(shoppingCartDAO.findById(id)).thenReturn(Optional.of(item));
		when(productDAO.findById(id)).thenReturn(Optional.of(product));
		shoppingCartService.updateCartItemQuantity(id,5);
		verify(shoppingCartDAO, times(1)).save(item);
	}

	@Test
	public void addItem() {
		Long id = 1L;
		CartItemBody cartItemBody = new CartItemBody();
		cartItemBody.setOrderId(id);
		cartItemBody.setProductId(id);
		when(orderDAO.findById(id)).thenReturn(Optional.ofNullable(order));
		when(productDAO.findById(id)).thenReturn(Optional.ofNullable(product));
		shoppingCartService.addItem(cartItemBody);
		verify(orderDAO, times(1)).save(order);


	}
}
