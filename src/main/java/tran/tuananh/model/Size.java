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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "Size")
public class Size {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "SizeId")
	private int sizeId;

	@Column(name = "SizeName")
	private String sizeName;

	@Column(name = "SizeStatus")
	private boolean sizeStatus;

	@JsonIgnore
	@ManyToMany(mappedBy = "sizes")
	private List<Product> products = new ArrayList<Product>();

//	@JsonIgnore
//	@OneToMany(mappedBy = "size")
//	private List<OrderDetail> orderDetails = new ArrayList<OrderDetail>();

	public Size() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Size(int sizeId, String sizeName, boolean sizeStatus, List<Product> products) {
		super();
		this.sizeId = sizeId;
		this.sizeName = sizeName;
		this.sizeStatus = sizeStatus;
		this.products = products;
	}

	public int getSizeId() {
		return sizeId;
	}

	public void setSizeId(int sizeId) {
		this.sizeId = sizeId;
	}

	public String getSizeName() {
		return sizeName;
	}

	public void setSizeName(String sizeName) {
		this.sizeName = sizeName;
	}

	public boolean isSizeStatus() {
		return sizeStatus;
	}

	public void setSizeStatus(boolean sizeStatus) {
		this.sizeStatus = sizeStatus;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	

}
