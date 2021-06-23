package tran.tuananh.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tran.tuananh.model.Order;
import tran.tuananh.model.Product;
import tran.tuananh.model.User;
import tran.tuananh.service.OrderService;
import tran.tuananh.service.UserService;

@RestController
@RequestMapping(value = "/api/v2")
@CrossOrigin(origins = "*")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@Autowired
	private UserService userService;

	@GetMapping("/orders")
	public ResponseEntity<List<Order>> getAllOrders() {
		List<Order> listOrder = orderService.getAllOrder();
		return new ResponseEntity<List<Order>>(listOrder, HttpStatus.OK);
	}

	@PostMapping(value = "/orders")
	private ResponseEntity<Order> addOrder(@Valid @RequestBody Order order) {
		order.getOrderDetails().forEach(orderDetails -> orderDetails.setOrder(order));
		order.setOrderStatus(true);
		if (order.getUser() == null) {
			order.setUser(userService.getUserById(Integer.valueOf(9)));
		}
		Order orderAdd = orderService.addOrder(order);
		return new ResponseEntity<Order>(orderAdd, HttpStatus.OK);
	}

	@GetMapping(value = "/orders/{id}")
	private ResponseEntity<Order> getOrderById(@PathVariable("id") int orderId) {
		Order order = orderService.getOrderById(orderId);
		return new ResponseEntity<Order>(order, HttpStatus.OK);
	}

	@PutMapping(value = "/orders/{id}")
	private ResponseEntity<Order> updateOrder(@PathVariable("id") int orderId, @RequestBody Order order) {
		Order orderUpdate = orderService.getOrderById(orderId);
		orderUpdate.setOrderDetails(order.getOrderDetails());
		orderUpdate.setOrderAmount(order.getOrderAmount());
		Order updateOrder = orderService.updateOrder(orderId, orderUpdate);
		return new ResponseEntity<Order>(updateOrder, HttpStatus.OK);
	}

	@DeleteMapping(value = "/orders/{id}")
//	@PreAuthorize("hasRole('ROLE_ADMIN')")
	private ResponseEntity<String> deleteOrder(@PathVariable("id") int orderId) {
		String res = orderService.deleteOrder(orderId);
		return new ResponseEntity<String>(res, HttpStatus.OK);
	}

	@DeleteMapping(value = "/orders")
//	@PreAuthorize("hasRole('ROLE_ADMIN')")
	private ResponseEntity<String> deleteAllOrder() {
		String res = orderService.deleteAllOrder();
		return new ResponseEntity<String>(res, HttpStatus.OK);
	}
}
