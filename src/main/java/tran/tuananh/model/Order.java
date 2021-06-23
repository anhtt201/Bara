package tran.tuananh.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Orders")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "OrderId")
	private int orderId;

	@Column(name = "OrderAmount")
	private int orderAmount;

	@Column(name = "OrderStatus")
	private boolean orderStatus;

	@ManyToOne
	@JoinColumn(name = "id", referencedColumnName = "id")
	private User user;

	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	private List<OrderDetails> orderDetails = new ArrayList<OrderDetails>();

	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Order(int orderId, int orderAmount, boolean orderStatus, User user, List<OrderDetails> orderDetails) {
		super();
		this.orderId = orderId;
		this.orderAmount = orderAmount;
		this.orderStatus = orderStatus;
		this.user = user;
		this.orderDetails = orderDetails;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(int orderAmount) {
		this.orderAmount = orderAmount;
	}

	public boolean isOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(boolean orderStatus) {
		this.orderStatus = orderStatus;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<OrderDetails> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetails> orderDetails) {
		this.orderDetails = orderDetails;
	}

}
