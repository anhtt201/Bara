package tran.tuananh.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "Brand")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "brandId")
public class Brand {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "BrandId")
	private int brandId;

	@Column(name = "BrandName", columnDefinition = "nvarchar(255)")
	private String brandName;

	@Column(name = "BrandImage")
	private String brandImg;

	@Column(name = "BrandStatus")
	private boolean brandStatus;

	@JsonIgnore
//	@JsonManagedReference(value = "pro_brand")
	@OneToMany(mappedBy = "brand", cascade = CascadeType.ALL)
	private List<Product> products = new ArrayList<Product>();

	public Brand() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Brand(int brandId, String brandName, String brandImg, boolean brandStatus, List<Product> products) {
		super();
		this.brandId = brandId;
		this.brandName = brandName;
		this.brandImg = brandImg;
		this.brandStatus = brandStatus;
		this.products = products;
	}

	public int getBrandId() {
		return brandId;
	}

	public void setBrandId(int brandId) {
		this.brandId = brandId;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getBrandImg() {
		return brandImg;
	}

	public void setBrandImg(String brandImg) {
		this.brandImg = brandImg;
	}

	public boolean isBrandStatus() {
		return brandStatus;
	}

	public void setBrandStatus(boolean brandStatus) {
		this.brandStatus = brandStatus;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

}
