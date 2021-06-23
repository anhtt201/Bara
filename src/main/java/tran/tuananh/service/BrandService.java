package tran.tuananh.service;

import java.util.List;

import tran.tuananh.model.Brand;

public interface BrandService {
	public List<Brand> getAllBrand();

	public Brand addBrand(Brand brand);

	public Brand getBrandById(int brandId);

	public Brand updateBrand(int brandId, Brand brand);

	public String deleteBrand(int brandId);
}
