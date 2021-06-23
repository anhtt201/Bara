package tran.tuananh.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tran.tuananh.model.Brand;
import tran.tuananh.model.Comment;
import tran.tuananh.repository.CommentRepository;
import tran.tuananh.service.CommentService;

@RestController
@RequestMapping(value = "/api/v2")
@CrossOrigin(origins = "*")
public class CommentController {

	@Autowired
	private CommentService service;

	@GetMapping(value = "/commentProductUser")
	private ResponseEntity<List<Comment>> getAllComment() {
		List<Comment> comments = service.getAllComment();
		return new ResponseEntity<List<Comment>>(comments, HttpStatus.OK);
	}
	
	@GetMapping(value = "/commentProductUser/{productId}")
	private ResponseEntity<List<Comment>> getAllCommentByProductId(@PathVariable("productId") int productId) {
		List<Comment> comments = service.getCommentByProductId(productId);
		return new ResponseEntity<List<Comment>>(comments, HttpStatus.OK);
	}

	@GetMapping(value = "/commentProductUser/{productId}/{userId}")
	private ResponseEntity<List<Comment>> getCommentByProductAndUser(@PathVariable Map<String, String> Ids) {
		int userId = Integer.valueOf(Ids.get("userId"));
		int productId = Integer.valueOf(Ids.get("productId"));
		List<Comment> comments = service.getCommentByProductAndUser(userId, productId);
		return new ResponseEntity<List<Comment>>(comments, HttpStatus.OK);
	}

	@PostMapping(value = "/commentProductUser/{productId}/{userId}")
	private ResponseEntity<Comment> addCommentByProductAndUser(@RequestBody Comment comment,
			@PathVariable Map<String, String> Ids) {
//		String date = String.valueOf(comment.getCreatedDay());
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX");
//		LocalDateTime newDate = LocalDateTime.parse(date, formatter);
//		comment.setCreatedDay(newDate);
		int userId = Integer.valueOf(Ids.get("userId"));
		int productId = Integer.valueOf(Ids.get("productId"));
		Comment newComment = service.addCommentByProductAndUser(comment, userId, productId);
		return new ResponseEntity<Comment>(newComment, HttpStatus.OK);
	}

}
