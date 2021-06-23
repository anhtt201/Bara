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
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "Catalog")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "catalogId")
public class Catalog {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CatalogId")
	private int catalogId;

	@Column(name = "CatalogName", columnDefinition = "nvarchar(255)")
	private String catalogName;

	@Column(name = "CatalogParentId")
	private int catalogParentId;

	@Column(name = "CatalogImage")
	private String catalogImg;

	@Column(name = "CatalogImageId")
	private String catalogImgId;

	@Column(name = "CatalogDescription", columnDefinition = "nvarchar(255)")
	private String catalogDescription;

	@Column(name = "CatalogStatus")
	private boolean catalogStatus;

	@Column(name = "CatalogIsHot")
	private boolean catalogIsHot;

	@Column(name = "CatalogType")
	private boolean catalogType;

	@JsonIgnore
//	@JsonBackReference(value = "pro_cat")
	@OneToMany(mappedBy = "catalog", cascade = CascadeType.ALL)
	private List<Product> products = new ArrayList<Product>();

	public Catalog() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Catalog(int catalogId, String catalogName, int catalogParentId, String catalogImg, String catalogImgId,
			String catalogDescription, boolean catalogStatus, boolean catalogIsHot, boolean catalogType,
			List<Product> products) {
		super();
		this.catalogId = catalogId;
		this.catalogName = catalogName;
		this.catalogParentId = catalogParentId;
		this.catalogImg = catalogImg;
		this.catalogImgId = catalogImgId;
		this.catalogDescription = catalogDescription;
		this.catalogStatus = catalogStatus;
		this.catalogIsHot = catalogIsHot;
		this.catalogType = catalogType;
		this.products = products;
	}

	public int getCatalogId() {
		return catalogId;
	}

	public void setCatalogId(int catalogId) {
		this.catalogId = catalogId;
	}

	public String getCatalogName() {
		return catalogName;
	}

	public void setCatalogName(String catalogName) {
		this.catalogName = catalogName;
	}

	public int getCatalogParentId() {
		return catalogParentId;
	}

	public void setCatalogParentId(int catalogParentId) {
		this.catalogParentId = catalogParentId;
	}

	public String getCatalogImg() {
		return catalogImg;
	}

	public void setCatalogImg(String catalogImg) {
		this.catalogImg = catalogImg;
	}

	public String getCatalogDescription() {
		return catalogDescription;
	}

	public void setCatalogDescription(String catalogDescription) {
		this.catalogDescription = catalogDescription;
	}

	public boolean isCatalogStatus() {
		return catalogStatus;
	}

	public void setCatalogStatus(boolean catalogStatus) {
		this.catalogStatus = catalogStatus;
	}

	public boolean isCatalogIsHot() {
		return catalogIsHot;
	}

	public void setCatalogIsHot(boolean catalogIsHot) {
		this.catalogIsHot = catalogIsHot;
	}

	public boolean isCatalogType() {
		return catalogType;
	}

	public void setCatalogType(boolean catalogType) {
		this.catalogType = catalogType;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public String getCatalogImgId() {
		return catalogImgId;
	}

	public void setCatalogImgId(String catalogImgId) {
		this.catalogImgId = catalogImgId;
	}

}
