package tran.tuananh.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tran.tuananh.model.Comment;
import tran.tuananh.model.User;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
	
	@Query(value = "SELECT c FROM Comment c WHERE c.product.productId = :productId")
	public List<Comment> getCommentByProductId(@Param("productId") int productId);
	
	@Query(value = "SELECT DISTINCT c FROM Comment c JOIN User u ON c.user.id = :userId")
	public List<Comment> getCommentByUserId(@Param("userId") int userId);
	
//	@Query(value = "SELECT DISTINCT c FROM Comment c JOIN User u ON c.user.id = :userId JOIN Product p ON c.product.productId = :productId")
	@Query(value = "SELECT c FROM Comment c WHERE c.user.id = :userId AND c.product.productId = :productId")
	public List<Comment> getCommentByProductAndUser(@Param("userId") int userId, @Param("productId") int productId);
}
