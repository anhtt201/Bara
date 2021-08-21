package tran.tuananh.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tran.tuananh.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

	@Query("select c.catalogId from Catalog c join Product p on p.catalog.catalogId = c.catalogId and p.productId = :id")
	public Integer getCatalog(@Param("id") int id);

	@Query("select p from Product p where p.productName like %:productName%")
	public List<Product> searchProduct(@Param("productName") String productName);
}
