package tran.tuananh.service;

import java.io.IOException;

import javax.mail.MessagingException;

import org.springframework.mail.MailException;

import tran.tuananh.model.Order;
import tran.tuananh.model.OrderDetails;
import tran.tuananh.model.User;

public interface MailService {

	public String sendEmail(User user, int orderId) throws MailException, MessagingException, IOException;

	public void sendEmailWithAttachment(User user) throws MailException, MessagingException;
}
