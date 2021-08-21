package tran.tuananh.model;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
//@Embeddable
@Table(name = "Comment")
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CommentId")
	private int commentId;

	@Column(name = "CommentContent", columnDefinition = "nvarchar(255)")
	private String commentContent;
	
	@JsonFormat(pattern = "dd-MM-yyyy")
	@Column(name = "CommentCreatedDay")
	private Date createdDay;

	@Column(name = "CommentRate")
	private int[] commentRate;

	@Column(name = "CommentStatus")
	private boolean commentStatus;

//	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id", referencedColumnName = "id")
	private User user;

	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ProductId", referencedColumnName = "productId")
	private Product product;

	public Comment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Comment(int commentId, String commentContent, Date createdDay, int[] commentRate, boolean commentStatus,
			User user, Product product) {
		super();
		this.commentId = commentId;
		this.commentContent = commentContent;
		this.createdDay = createdDay;
		this.commentRate = commentRate;
		this.commentStatus = commentStatus;
		this.user = user;
		this.product = product;
	}

	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	public Date getCreatedDay() {
		return createdDay;
	}

	public void setCreatedDay(Date createdDay) {
		this.createdDay = createdDay;
	}

	public int[] getCommentRate() {
		return commentRate;
	}

	public void setCommentRate(int[] commentRate) {
		this.commentRate = commentRate;
	}

	public boolean isCommentStatus() {
		return commentStatus;
	}

	public void setCommentStatus(boolean commentStatus) {
		this.commentStatus = commentStatus;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}
