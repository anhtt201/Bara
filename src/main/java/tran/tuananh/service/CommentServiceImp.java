package tran.tuananh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tran.tuananh.model.Comment;
import tran.tuananh.model.Product;
import tran.tuananh.model.User;
import tran.tuananh.repository.CommentRepository;
import tran.tuananh.repository.ProductRepository;
import tran.tuananh.repository.UserRepository;

@Service
public class CommentServiceImp implements CommentService {

	@Autowired
	private CommentRepository commentRepo;

	@Autowired
	private ProductRepository productRepo;

	@Autowired
	private UserRepository userRepo;

	@Override
	public List<Comment> getAllComment() {
		// TODO Auto-generated method stub
		return commentRepo.findAll();
	}

	
	@Override
	public List<Comment> getCommentByProductId(int proId) {
		// TODO Auto-generated method stub
		return commentRepo.getCommentByProductId(proId);
	}


	@Override
	public List<Comment> getCommentByProductAndUser(int userId, int proId) {
		// TODO Auto-generated method stub
		return commentRepo.getCommentByProductAndUser(userId, proId);
	}

	@Override
	public Comment addCommentByProductAndUser(Comment comment, int userId, int proId) {
		// TODO Auto-generated method stub
		Product product = productRepo.findById(proId).get();
		User user = userRepo.findById(userId).get();
		product.getComments().add(comment);
		user.getComments().add(comment);
		productRepo.saveAndFlush(product);
		userRepo.saveAndFlush(user);
		comment.setProduct(product);
		comment.setUser(user);
		return commentRepo.save(comment);
	}

	@Override
	public Comment updateComment(int commentId, Comment comment) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteComment(int commentId) {
		// TODO Auto-generated method stub
		return null;
	}

}
