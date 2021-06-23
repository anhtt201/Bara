package tran.tuananh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tran.tuananh.model.Brand;
import tran.tuananh.repository.BrandRepository;

@Service
public class BrandServiceImp implements BrandService {

	@Autowired
	private BrandRepository brandRepo;

	@Override
	public List<Brand> getAllBrand() {
		// TODO Auto-generated method stub
		return brandRepo.findAll();
	}

	@Override
	public Brand addBrand(Brand brand) {
		// TODO Auto-generated method stub
		return brandRepo.save(brand);
	}

	@Override
	public Brand getBrandById(int brandId) {
		// TODO Auto-generated method stub
		return brandRepo.findById(brandId).get();
	}

	@Override
	public Brand updateBrand(int brandId, Brand brand) {
		// TODO Auto-generated method stub
		Brand brandUpdate = brandRepo.findById(brandId).get();
		brandUpdate.setBrandName(brand.getBrandName());
		brandUpdate.setBrandImg(brand.getBrandImg());
		brandUpdate.setBrandStatus(brand.isBrandStatus());
		brandUpdate.setProducts(brand.getProducts());
		return brandRepo.save(brandUpdate);
	}

	@Override
	public String deleteBrand(int brandId) {
		// TODO Auto-generated method stub
		Brand brand = brandRepo.findById(brandId).get();
		brandRepo.delete(brand);
		return "Deleted!";
	}

}
