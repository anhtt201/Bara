package tran.tuananh.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "Color")
public class Color {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ColorId")
	private int colorId;

	@Column(name = "ColorName")
	private String colorName;

	@Column(name = "ColorStatus")
	private boolean colorStatus;

	@JsonIgnore
	@ManyToMany(mappedBy = "colors")
	private List<Product> products = new ArrayList<Product>();

//	@JsonIgnore
//	@OneToMany(mappedBy = "color")
//	private List<OrderDetails> orderDetails = new ArrayList<OrderDetails>();

	public Color() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Color(int colorId, String colorName, boolean colorStatus, List<Product> products) {
		super();
		this.colorId = colorId;
		this.colorName = colorName;
		this.colorStatus = colorStatus;
		this.products = products;
//		this.orderDetails = orderDetails;
	}

	public int getColorId() {
		return colorId;
	}

	public void setColorId(int colorId) {
		this.colorId = colorId;
	}

	public String getColorName() {
		return colorName;
	}

	public void setColorName(String colorName) {
		this.colorName = colorName;
	}

	public boolean isColorStatus() {
		return colorStatus;
	}

	public void setColorStatus(boolean colorStatus) {
		this.colorStatus = colorStatus;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

//	public List<OrderDetails> getOrderDetails() {
//		return orderDetails;
//	}
//
//	public void setOrderDetails(List<OrderDetails> orderDetails) {
//		this.orderDetails = orderDetails;
//	}

}
