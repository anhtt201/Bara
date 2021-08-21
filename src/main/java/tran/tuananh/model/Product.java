package tran.tuananh.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "Product")
//@JsonIdentityInfo(generator = ObjectIdGenerators..class, property = "productId")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ProductId")
	private int productId;

	@Column(name = "ProductName", columnDefinition = "nvarchar(255)")
	private String productName;

	@Column(name = "ProductPriceIn")
	private float productPriceIn;

	@Column(name = "ProductPriceOut")
	private float productPriceOut;

	@Column(name = "ProductDiscount")
	private float productDiscount;

	@Column(name = "ProductImage", columnDefinition = "ntext")
	private String productImg;

//	@Column(name = "ProductImageId")
//	private String productImgId;

	@Column(name = "ProductDescription", columnDefinition = "nvarchar(255)")
	private String productDescription;

	@Column(name = "ProductCreateDay")
	private String productCreatedDay;

	@Column(name = "ProductQuantity")
	private int productQuantity;

	@Column(name = "ProductIsHot")
	private boolean productIsHot;

	@Column(name = "ProductStatus")
	private boolean productStatus;

//	@JsonBackReference(value = "pro_brand")
	@ManyToOne
	@JoinColumn(name = "BrandId", referencedColumnName = "brandId")
	private Brand brand;

//	@JsonManagedReference(value = "pro_cat")
//	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "CatalogId", referencedColumnName = "catalogId")
	private Catalog catalog;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "Product_Size", joinColumns = @JoinColumn(name = "Product_Id", referencedColumnName = "productId"), inverseJoinColumns = @JoinColumn(name = "Size_Id", referencedColumnName = "sizeId"))
	private List<Size> sizes = new ArrayList<Size>();

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "Product_Color", joinColumns = @JoinColumn(name = "Product_Id", referencedColumnName = "productId"), inverseJoinColumns = @JoinColumn(name = "Color_Id", referencedColumnName = "colorId"))
	private List<Color> colors = new ArrayList<Color>();

//	@Embedded
//	@JsonIgnore
	@OneToMany(mappedBy = "product")
	private List<Comment> comments = new ArrayList<Comment>();

	@OneToMany(mappedBy = "product")
	private List<News> news = new ArrayList<News>();

	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Product(int productId, String productName, float productPriceIn, float productPriceOut,
			float productDiscount, String productImg, String productDescription,
			String productCreatedDay, int productQuantity, boolean productIsHot, boolean productStatus, Brand brand,
			Catalog catalog, List<Size> sizes, List<Color> colors,
			List<Comment> comments, List<News> news) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productPriceIn = productPriceIn;
		this.productPriceOut = productPriceOut;
		this.productDiscount = productDiscount;
		this.productImg = productImg;
//		this.productImgId = productImgId;
		this.productDescription = productDescription;
		this.productCreatedDay = productCreatedDay;
		this.productQuantity = productQuantity;
		this.productIsHot = productIsHot;
		this.productStatus = productStatus;
		this.brand = brand;
		this.catalog = catalog;
		this.sizes = sizes;
		this.colors = colors;
		this.comments = comments;
		this.news = news;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public float getProductPriceIn() {
		return productPriceIn;
	}

	public void setProductPriceIn(float productPriceIn) {
		this.productPriceIn = productPriceIn;
	}

	public float getProductPriceOut() {
		return productPriceOut;
	}

	public void setProductPriceOut(float productPriceOut) {
		this.productPriceOut = productPriceOut;
	}

	public float getProductDiscount() {
		return productDiscount;
	}

	public void setProductDiscount(float productDiscount) {
		this.productDiscount = productDiscount;
	}

	public String getProductImg() {
		return productImg;
	}

	public void setProductImg(String productImg) {
		this.productImg = productImg;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public String getProductCreatedDay() {
		return productCreatedDay;
	}

	public void setProductCreatedDay(String productCreatedDay) {
		this.productCreatedDay = productCreatedDay;
	}

	public int getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(int productQuantity) {
		this.productQuantity = productQuantity;
	}

	public boolean isProductIsHot() {
		return productIsHot;
	}

	public void setProductIsHot(boolean productIsHot) {
		this.productIsHot = productIsHot;
	}

	public boolean isProductStatus() {
		return productStatus;
	}

	public void setProductStatus(boolean productStatus) {
		this.productStatus = productStatus;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public Catalog getCatalog() {
		return catalog;
	}

	public void setCatalog(Catalog catalog) {
		this.catalog = catalog;
	}

	public List<Size> getSizes() {
		return sizes;
	}

	public void setSizes(List<Size> sizes) {
		this.sizes = sizes;
	}

	public List<Color> getColors() {
		return colors;
	}

	public void setColors(List<Color> colors) {
		this.colors = colors;
	}

	public List<Comment> getComments() {
		return comments;
	}
	
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public List<News> getNews() {
		return news;
	}

	public void setNews(List<News> news) {
		this.news = news;
	}

//	public String getProductImgId() {
//		return productImgId;
//	}
//
//	public void setProductImgId(String productImgId) {
//		this.productImgId = productImgId;
//	}

}
