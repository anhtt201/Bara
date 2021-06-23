package tran.tuananh.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tran.tuananh.model.Order;
import tran.tuananh.model.User;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
	List<Order> findOrderByUser(User user);
}
