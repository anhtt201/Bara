package tran.tuananh.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "News")
public class News {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "NewsId")
	private int newsId;

	@Column(name = "NewsTitle")
	private String newsTitle;

	@Column(name = "NewsContent")
	private String newsContent;

	@Column(name = "NewsAuthor")
	private String newsAuthor;

	@Column(name = "NewsDescription")
	private String newsDescription;

	@Column(name = "NewsImg")
	private String newsImg;

	@Column(name = "NewsCreatedDay")
	private Date newsCreatedDay;

	@Column(name = "NewsTag")
	private String newsTag;

	@Column(name = "NewsStatus")
	private boolean newsStatus;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ProductId", referencedColumnName = "productId")
	private Product product;

	public News() {
		super();
		// TODO Auto-generated constructor stub
	}

	public News(int newsId, String newsTitle, String newsContent, String newsAuthor, String newsDescription,
			String newsImg, Date newsCreatedDay, String newsTag, boolean newsStatus, Product product) {
		super();
		this.newsId = newsId;
		this.newsTitle = newsTitle;
		this.newsContent = newsContent;
		this.newsAuthor = newsAuthor;
		this.newsDescription = newsDescription;
		this.newsImg = newsImg;
		this.newsCreatedDay = newsCreatedDay;
		this.newsTag = newsTag;
		this.newsStatus = newsStatus;
		this.product = product;
	}

	public int getNewsId() {
		return newsId;
	}

	public void setNewsId(int newsId) {
		this.newsId = newsId;
	}

	public String getNewsTitle() {
		return newsTitle;
	}

	public void setNewsTitle(String newsTitle) {
		this.newsTitle = newsTitle;
	}

	public String getNewsContent() {
		return newsContent;
	}

	public void setNewsContent(String newsContent) {
		this.newsContent = newsContent;
	}

	public String getNewsAuthor() {
		return newsAuthor;
	}

	public void setNewsAuthor(String newsAuthor) {
		this.newsAuthor = newsAuthor;
	}

	public String getNewsDescription() {
		return newsDescription;
	}

	public void setNewsDescription(String newsDescription) {
		this.newsDescription = newsDescription;
	}

	public String getNewsImg() {
		return newsImg;
	}

	public void setNewsImg(String newsImg) {
		this.newsImg = newsImg;
	}

	public Date getNewsCreatedDay() {
		return newsCreatedDay;
	}

	public void setNewsCreatedDay(Date newsCreatedDay) {
		this.newsCreatedDay = newsCreatedDay;
	}

	public String getNewsTag() {
		return newsTag;
	}

	public void setNewsTag(String newsTag) {
		this.newsTag = newsTag;
	}

	public boolean isNewsStatus() {
		return newsStatus;
	}

	public void setNewsStatus(boolean newsStatus) {
		this.newsStatus = newsStatus;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}
