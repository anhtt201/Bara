package tran.tuananh.controller;

import java.io.IOException;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tran.tuananh.model.Order;
import tran.tuananh.model.OrderDetails;
import tran.tuananh.model.User;
import tran.tuananh.repository.UserRepository;
import tran.tuananh.service.MailService;
import tran.tuananh.service.UserService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/mail")
public class MailController {

	@Autowired
	private MailService mailService;

	@Autowired
	private UserRepository userRepo;

	@PostMapping(value = "/sendMail/{userId}/{orderId}")
	private ResponseEntity<String> sendMail(@PathVariable("userId") int userId, @PathVariable("orderId") int orderId) throws MailException, MessagingException, IOException {
		User user = userRepo.findById(userId).get();
		String res = mailService.sendEmail(user, orderId);
		return new ResponseEntity<String>(res, HttpStatus.OK);
	}
}
