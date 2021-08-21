package tran.tuananh.service;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import tran.tuananh.model.Order;
import tran.tuananh.model.OrderDetails;
import tran.tuananh.model.Product;
import tran.tuananh.model.User;
import tran.tuananh.repository.OrderDetailsRepository;

@Service
public class MailServiceImp implements MailService {

	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	private OrderDetailsRepository orderDetailRepo;

	@Autowired
	private OrderDetailsService orderDetailService;

	@Autowired
	private OrderService orderService;

	@Override
	public String sendEmail(User user, int orderId) throws MailException, MessagingException, IOException {
		// TODO Auto-generated method stub

		NumberFormat defaultFormat = NumberFormat.getCurrencyInstance();
		Locale locale = new Locale("vn", "VN");
		NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);

		Order order = orderService.getOrderById(orderId);
		List<OrderDetails> listOrderDetails = orderDetailRepo.getOrderDetails(orderId);
		List<Product> product = new ArrayList<Product>();
		double total = 0;
		for (OrderDetails od : listOrderDetails) {
			total += od.getOrderDetailsQuantity() * (od.getProduct().getProductPriceOut());
		}
//		OrderDetails orderDetail = orderDetailService.getOrderDetailsById(order);
//		String productImg = product.getProductImg().split(";")[0];
		MimeMessage msg = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(msg, true, "utf-8");
		String mailHeader = "<!DOCTYPE html>\r\n" + "<html lang=\"en\">\r\n" + "\r\n" + "<head>\r\n"
				+ "    <meta charset=\"UTF-8\">\r\n"
				+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
				+ "    <title>Document</title>\r\n" + "\r\n" + "    <style>\r\n" + "        .container-fluid {\r\n"
				+ "            padding: 15px;\r\n" + "        }\r\n" + "\r\n" + " .thanks-text{color:#d93025\r\n}\r\n"
				+ "\r\n" + "img {\r\n" + "            max-width: 50%;\r\n" + "        }\r\n" + "\r\n"
				+ "        .row {\r\n" + "            display: flex;\r\n" + "        }\r\n" + "\r\n"
				+ "        .col-md-3 {\r\n" + "            width: calc(100%/4);\r\n" + "        }\r\n" + "\r\n"
				+ "        .col-md-6 {\r\n" + "            width: calc(100%/2);\r\n" + "        }\r\n" + "\r\n"
				+ "        .text-center {\r\n" + "            text-align: center;\r\n" + "        }\r\n"
				+ "    </style>\r\n" + "</head>\r\n" + "\r\n" + "<body>\r\n" + "\r\n"
				+ "    <div class=\"container-fluid\">\r\n" + "        <h1 class=\"text-center\">Bara</h1>\r\n"
				+ "        <hr>\r\n" + "        <h3>Chào Tuấn Anh</h3>\r\n"
				+ "        <p class=\"thanks-text\">Cảm ơn vì đã mua hàng</p>\r\n";
		StringBuilder mailBody = new StringBuilder();
		mailBody.append("<p>Mã đơn hàng:" + orderId + "</p>\r\n");
		listOrderDetails.forEach(o -> {
			mailBody.append("        <div class=\"row\">\r\n            <div class=\"col-md-3\">\r\n"
					+ "                <p>Hình ảnh</p>\r\n     </div>\r\n"
					+ "            <div class=\"col-md-3\">\r\n                <p>Tên sản phẩm</p>\r\n"
					+ "            </div>\r\n            <div class=\"col-md-3\">\r\n"
					+ "                <p>Số lượng sản phẩm</p>\r\n           </div>\r\n"
					+ "            <div class=\"col-md-3\">\r\n                <p>Giá sản phẩm</p>\r\n"
					+ "            </div>\r\n" + "        </div>\r\n    <div class=\"row\">\r\n"
					+ "            <div class=\"col-md-3\">\r\n              <img src="
					+ "https://upload.wikimedia.org/wikipedia/commons/thumb/c/cf/Angular_full_color_logo.svg/1200px-Angular_full_color_logo.svg.png"
					+ ">            </div>\r\n            <div class=\"col-md-3\">\r\n" + "<p>"
					+ o.getProduct().getProductName() + "</p>\r\n" + "            </div>\r\n"
					+ "            <div class=\"col-md-3\">\r\n" + "                " + o.getOrderDetailsQuantity()
					+ "\r\n" + "            </div>\r\n" + "            <div class=\"col-md-3\">\r\n"
					+ "                " + currencyFormatter.format(o.getProduct().getProductPriceOut()) + "00\r\n"
					+ "</div></div>");
		});

		mailBody.append("<div class=\"row\">\r\n" + "            <div class=\"col-md-6\">\r\n"
				+ "                <p>Tổng giá trị đơn hàng: </p>\r\n" + "                <p>Khuyến mãi: </p>\r\n"
				+ "                <p>Địa chỉ nhận hàng: </p>\r\n" + "            </div>\r\n"
				+ "            <div class=\"col-md-6\">\r\n" + "                <p>" + "</p>\r\n<p>"
				+ currencyFormatter.format(total) + ".000</p>\r\n" + "                <p>" + "Không có" + "</p>\r\n"
				+ "                <p> " + order.getUser().getAddress() + " </p>\r\n" + "            </div>\r\n"
				+ "        </div>\r\n" + "    </div>\r\n" + "\r\n" + "</body>\r\n" + "\r\n" + "</html>");

		StringBuilder content = new StringBuilder();
		content.append(mailHeader);
		content.append(mailBody);
		String mailContent = content.toString();
		msg.setContent(mailContent, "text/html;charset=utf-8");
		helper.setTo(user.getEmail());
		helper.setSubject("Thank you for shopping!");

		javaMailSender.send(msg);

		return "Successfully!";
	}

	@Override
	public void sendEmailWithAttachment(User user) throws MailException, MessagingException {

	}

}
