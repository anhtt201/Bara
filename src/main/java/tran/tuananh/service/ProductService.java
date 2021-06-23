package tran.tuananh.service;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import tran.tuananh.model.Product;

public interface ProductService {
	public List<Product> getAllProduct();

	public Product addProduct(Product pro);

	public Product getProductById(int proId);

	public Product updateProduct(int proId, Product pro);

	public String deleteProduct(int proId);

	public String deleteAllProduct();
}
