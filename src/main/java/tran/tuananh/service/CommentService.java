package tran.tuananh.service;

import java.util.List;

import tran.tuananh.model.Comment;
import tran.tuananh.model.User;

public interface CommentService {

	public List<Comment> getAllComment();

	public List<Comment> getCommentByProductId(int proId);
	
	public List<Comment> getCommentByProductAndUser(int userId, int proId);
	
	public Comment addCommentByProductAndUser(Comment comment, int userId, int proId);

	public Comment updateComment(int commentId, Comment comment);

	public String deleteComment(int commentId);

}
