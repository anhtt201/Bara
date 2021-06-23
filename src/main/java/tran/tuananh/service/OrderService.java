package tran.tuananh.service;

import java.util.List;

import tran.tuananh.model.Order;

public interface OrderService {

	public List<Order> getAllOrder();

	public Order addOrder(Order order);

	public Order getOrderById(int orderId);

	public Order updateOrder(int orderId, Order order);

	public String deleteOrder(int orderId);

	public String deleteAllOrder();
}
