package tran.tuananh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tran.tuananh.model.OrderDetails;
import tran.tuananh.repository.OrderDetailsRepository;

@Service
public class OrderDetailsServiceImp implements OrderDetailsService {
	@Autowired
	private OrderDetailsRepository orderDetailsRepo;

	@Override
	public List<OrderDetails> getAllOrderDetails() {
		// TODO Auto-generated method stub
		return orderDetailsRepo.findAll();
	}

	@Override
	public OrderDetails addOrderDetails(OrderDetails orderDetails) {
		// TODO Auto-generated method stub
		return orderDetailsRepo.save(orderDetails);
	}

	@Override
	public OrderDetails getOrderDetailsById(int orderDetailsId) {
		// TODO Auto-generated method stub
		return orderDetailsRepo.findById(orderDetailsId).get();
	}

	@Override
	public OrderDetails updateOrderDetails(int orderId, OrderDetails orderDetails) {
		// TODO Auto-generated method stub
		OrderDetails orderDetailsUpdate = orderDetailsRepo.findById(orderId).get();
		orderDetailsUpdate.setOrderDetailsQuantity(orderDetails.getOrderDetailsQuantity());
		orderDetailsUpdate.setProduct(orderDetails.getProduct());
		return orderDetailsRepo.save(orderDetailsUpdate);
	}

	@Override
	public String deleteOrderDetails(int orderDetailsId) {
		// TODO Auto-generated method stub
		OrderDetails orderDelete = orderDetailsRepo.findById(orderDetailsId).get();
		orderDetailsRepo.delete(orderDelete);
		;
		return "Deleted!";
	}

	@Override
	public String deleteAllOrderDetails() {
		// TODO Auto-generated method stub
		orderDetailsRepo.deleteAll();
		return "Deleted all!";
	}
}
