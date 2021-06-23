package tran.tuananh.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tran.tuananh.model.OrderDetails;
import tran.tuananh.service.OrderDetailsService;

@RestController
@RequestMapping(value = "/api/v2")
@CrossOrigin(origins = "*")
public class OrderDetailsController {

	@Autowired
	private OrderDetailsService service;

	@GetMapping(value = "/orderDetails")
	private ResponseEntity<List<OrderDetails>> getAllOrderDetails() {
		List<OrderDetails> listOrderDetails = service.getAllOrderDetails();
		return new ResponseEntity<List<OrderDetails>>(listOrderDetails, HttpStatus.OK);
	}

	@PostMapping(value = "/orderDetails")
	private ResponseEntity<OrderDetails> addOrderDetails(@Valid @RequestBody OrderDetails orderDetails) {
		OrderDetails orderDetailsAdd = service.addOrderDetails(orderDetails);
		return new ResponseEntity<OrderDetails>(orderDetailsAdd, HttpStatus.OK);
	}

	@GetMapping(value = "/orderDetails/{id}")
	private ResponseEntity<OrderDetails> getOrderDetailsById(@PathVariable("id") int orderDetailsId) {
		OrderDetails orderDetails = service.getOrderDetailsById(orderDetailsId);
		return new ResponseEntity<OrderDetails>(orderDetails, HttpStatus.OK);
	}

	@PutMapping(value = "/orderDetails/{id}")
	private ResponseEntity<OrderDetails> updateOrderDetails(@PathVariable("id") int orderDetailsId,
			@RequestBody OrderDetails orderDetails) {
		OrderDetails orderDetailsUpdate = service.updateOrderDetails(orderDetailsId, orderDetails);
		return new ResponseEntity<OrderDetails>(orderDetailsUpdate, HttpStatus.OK);
	}

	@DeleteMapping(value = "/orderDetails/{id}")
//	@PreAuthorize("hasRole('ROLE_ADMIN')")
	private ResponseEntity<String> deleteOrderDetails(@PathVariable("id") int orderDetailsId) {
		String res = service.deleteOrderDetails(orderDetailsId);
		return new ResponseEntity<String>(res, HttpStatus.OK);
	}

	@DeleteMapping(value = "/orderDetails")
//	@PreAuthorize("hasRole('ROLE_ADMIN')")
	private ResponseEntity<String> deleteAllOrderDetails() {
		String res = service.deleteAllOrderDetails();
		return new ResponseEntity<String>(res, HttpStatus.OK);
	}
}
