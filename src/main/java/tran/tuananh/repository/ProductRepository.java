package tran.tuananh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tran.tuananh.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{

	@Query("select c.catalogId from Catalog c join Product p on p.catalog.catalogId = c.catalogId and p.productId = :id")
	public Integer getCatalog(@Param("id") int id);
}
