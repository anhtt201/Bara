package tran.tuananh.service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tran.tuananh.model.Brand;
import tran.tuananh.model.Catalog;
import tran.tuananh.model.Comment;
import tran.tuananh.model.Product;
import tran.tuananh.repository.BrandRepository;
import tran.tuananh.repository.CatalogRepository;
import tran.tuananh.repository.ProductRepository;

@Service
public class ProductServiceImp implements ProductService {

	@Autowired
	private ProductRepository proRepo;

	@Autowired
	private CatalogRepository catRepo;

	@Autowired
	private BrandRepository brandRepo;

	@Override
	public List<Product> getAllProduct() {
		// TODO Auto-generated method stub
		return proRepo.findAll();
	}

	@Override
	public Product addProduct(Product pro) {
		// TODO Auto-generated method stub
		return proRepo.save(pro);
	}

	@Override
	public Product getProductById(int proId) {
		// TODO Auto-generated method stub
		return proRepo.findById(proId).get();
	}

	@Override
	public Product updateProduct(int proId, Product pro) {
		// TODO Auto-generated method stub
		Product proUpdate = proRepo.findById(proId).get();
		proUpdate.setProductName(pro.getProductName());
		proUpdate.setProductPriceIn(pro.getProductPriceIn());
		proUpdate.setProductPriceOut(pro.getProductPriceOut());
		proUpdate.setProductDiscount(pro.getProductDiscount());
		proUpdate.setProductImg(pro.getProductImg());
		proUpdate.setProductDescription(pro.getProductDescription());
		proUpdate.setProductCreatedDay(pro.getProductCreatedDay());
		proUpdate.setProductQuantity(pro.getProductQuantity());
		proUpdate.setProductIsHot(pro.isProductIsHot());
		proUpdate.setProductStatus(pro.isProductStatus());
		proUpdate.setCatalog(pro.getCatalog());
		proUpdate.setBrand(pro.getBrand());
		proUpdate.setColors(pro.getColors());
		proUpdate.setSizes(pro.getSizes());
		return proRepo.save(proUpdate);
	}

	@Override
	public String deleteProduct(int proId) {
		// TODO Auto-generated method stub
		Product pro = proRepo.findById(proId).get();
		for (Comment comment : pro.getComments()) {
			comment.setProduct(null);
		}
		pro.setComments(null);
		if (pro.getCatalog() != null)
			pro.getCatalog().setProducts(null);
		pro.setCatalog(null);
		proRepo.delete(pro);
		return "Deleted!";
	}

	@Override
	public String deleteAllProduct() {
		// TODO Auto-generated method stub
		proRepo.deleteAll();
		return "Deleted all!";
	}

	@Override
	public List<Product> searchProduct(String productName) {
		// TODO Auto-generated method stub
		return proRepo.searchProduct(productName);
	}
	
	private static final String NAME_PATTERN = "([A-Z]{1})([a-z0-9 \\p{L}]+)";
	private static final Pattern pattern = Pattern.compile(NAME_PATTERN, Pattern.UNICODE_CHARACTER_CLASS);

	@Override
	public boolean checkProductName(String proName) {
		Matcher matcher = pattern.matcher(proName);
		return matcher.matches();

	}

	@Override
	public boolean checkProductLength(String proName) {
		if (proName.length() >= 6) {
			return true;
		}
		return false;
	}

}
