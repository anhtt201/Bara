package tran.tuananh.service;

import java.util.List;

import tran.tuananh.model.OrderDetails;

public interface OrderDetailsService {

	public List<OrderDetails> getAllOrderDetails();

	public OrderDetails addOrderDetails(OrderDetails orderDetail);

	public OrderDetails getOrderDetailsById(int orderDetailId);

	public OrderDetails updateOrderDetails(int orderDetailId, OrderDetails orderDetail);

	public String deleteOrderDetails(int orderDetailId);

	public String deleteAllOrderDetails();
}
