package tran.tuananh.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tran.tuananh.model.OrderDetails;
import tran.tuananh.model.Product;

@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Integer> {

	@Query("SELECT o FROM OrderDetails o where o.order.orderId = :orderId")
	public List<OrderDetails> getOrderDetails(@Param("orderId") int orderId);
}
