package tran.tuananh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tran.tuananh.model.Order;
import tran.tuananh.repository.OrderRepository;

@Service
public class OrderServiceImp implements OrderService {

	@Autowired
	private OrderRepository orderRepo;

	@Override
	public List<Order> getAllOrder() {
		// TODO Auto-generated method stub
		return orderRepo.findAll();
	}

	@Override
	public Order addOrder(Order order) {
		// TODO Auto-generated method stub
		return orderRepo.save(order);
	}

	@Override
	public Order getOrderById(int orderId) {
		// TODO Auto-generated method stub
		return orderRepo.findById(orderId).get();
	}

	@Override
	public Order updateOrder(int orderId, Order order) {
		// TODO Auto-generated method stub
		Order orderUpdate = orderRepo.findById(orderId).get();
		orderUpdate.setOrderAmount(order.getOrderAmount());
		orderUpdate.setOrderDetails(order.getOrderDetails());
		orderUpdate.setOrderStatus(order.isOrderStatus());
		return orderRepo.save(orderUpdate);
	}

	@Override
	public String deleteOrder(int orderId) {
		// TODO Auto-generated method stub
		Order orderDelete = orderRepo.findById(orderId).get();
		orderRepo.delete(orderDelete);
		;
		return "Deleted!";
	}

	@Override
	public String deleteAllOrder() {
		// TODO Auto-generated method stub
		orderRepo.deleteAll();
		return "Deleted all!";
	}

}
