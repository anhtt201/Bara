package tran.tuananh.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "OrderDetails")
public class OrderDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "OrderDetailsId")
	private int orderDetailsId;

	@Column(name = "OrderDetailsQuantity")
	private int orderDetailsQuantity;

	@ManyToOne
	@JoinColumn(name = "ProductId", referencedColumnName = "productId")
	private Product product;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "OrderId", referencedColumnName = "orderId")
	private Order order;

	public OrderDetails(int orderDetailsId, int orderDetailsQuantity, Product product, Order order) {
		super();
		this.orderDetailsId = orderDetailsId;
		this.orderDetailsQuantity = orderDetailsQuantity;
		this.product = product;
		this.order = order;
	}

	public OrderDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getOrderDetailsId() {
		return orderDetailsId;
	}

	public void setOrderDetailsId(int orderDetailsId) {
		this.orderDetailsId = orderDetailsId;
	}

	public int getOrderDetailsQuantity() {
		return orderDetailsQuantity;
	}

	public void setOrderDetailsQuantity(int orderDetailsQuantity) {
		this.orderDetailsQuantity = orderDetailsQuantity;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

}
