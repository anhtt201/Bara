package tran.tuananh.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tran.tuananh.model.Catalog;
import tran.tuananh.model.Product;
import tran.tuananh.service.CatalogService;
import tran.tuananh.service.ProductService;

@RestController
@RequestMapping(value = "/api/v2")
@CrossOrigin(origins = "*")
public class ProductController {

	@Autowired
	private ProductService service;

	@Autowired
	private CatalogService catService;

	@GetMapping(value = "/product")
	private ResponseEntity<List<Product>> getAllProduct() {
		List<Product> listPro = service.getAllProduct();
		return new ResponseEntity<List<Product>>(listPro, HttpStatus.OK);
	}

	@PostMapping(value = "/product")
	private ResponseEntity<Product> addProduct(@Valid @RequestBody Product product) {
		Product pro = service.addProduct(product);
		return new ResponseEntity<Product>(pro, HttpStatus.OK);
	}

	@GetMapping(value = "/product/{id}")
	private ResponseEntity<Product> getProductById(@PathVariable("id") int proId) {
		Product pro = service.getProductById(proId);
		return new ResponseEntity<Product>(pro, HttpStatus.OK);
	}

	@PutMapping(value = "/product/{id}")
	private ResponseEntity<Product> updateProduct(@PathVariable("id") int proId, @RequestBody Product product) {
		Product pro = service.updateProduct(proId, product);
		return new ResponseEntity<Product>(pro, HttpStatus.OK);
	}

	@DeleteMapping(value = "/product/{id}")
//	@PreAuthorize("hasRole('ROLE_ADMIN')")
	private ResponseEntity<String> deleteProduct(@PathVariable("id") int proId) {
		String res = service.deleteProduct(proId);
		return new ResponseEntity<String>(res, HttpStatus.OK);
	}

	@DeleteMapping(value = "/product")
//	@PreAuthorize("hasRole('ROLE_ADMIN')")
	private ResponseEntity<String> deleteAllProduct() {
		String res = service.deleteAllProduct();
		return new ResponseEntity<String>(res, HttpStatus.OK);
	}

}
