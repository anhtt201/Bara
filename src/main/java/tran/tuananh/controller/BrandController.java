package tran.tuananh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RestController;

import tran.tuananh.model.Brand;
import tran.tuananh.service.BrandService;

@RestController
@RequestMapping(value = "/api/v2")
@CrossOrigin(origins = "*")
public class BrandController {

	@Autowired
	private BrandService service;

	@GetMapping(value = "/brand")
	private ResponseEntity<List<Brand>> getAllBrand() {
		List<Brand> listBrand = service.getAllBrand();
		return new ResponseEntity<List<Brand>>(listBrand, HttpStatus.OK);
	}

	@PostMapping(value = "/brand")
	private ResponseEntity<Brand> addBrand(@RequestBody Brand brand) {
		Brand brandAdd = service.addBrand(brand);
		return new ResponseEntity<Brand>(brandAdd, HttpStatus.OK);
	}

	@GetMapping(value = "/brand/{id}")
	private ResponseEntity<Brand> getCatalogById(@PathVariable("id") int brandId) {
		Brand brand = service.getBrandById(brandId);
		return new ResponseEntity<Brand>(brand, HttpStatus.OK);
	}

	@PutMapping(value = "/brand/{id}")
	private ResponseEntity<Brand> updateBrand(@PathVariable("id") int brandId, @RequestBody Brand brand) {
		Brand brandUpdate = service.updateBrand(brandId, brand);
		return new ResponseEntity<Brand>(brandUpdate, HttpStatus.OK);
	}

	@DeleteMapping(value = "/brand/{id}")
//	@PreAuthorize("hasRole('ROLE_ADMIN')")
	private ResponseEntity<String> deleteBrand(@PathVariable("id") int brandId) {
		String res = service.deleteBrand(brandId);
		return new ResponseEntity<String>(res, HttpStatus.OK);
	}
}
