package tran.tuananh.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tran.tuananh.model.Catalog;
import tran.tuananh.model.Product;
import tran.tuananh.repository.CatalogRepository;
import tran.tuananh.repository.ProductRepository;

@Service
public class CatalogServiceImp implements CatalogService {

	@Autowired
	private CatalogRepository catRepo;
	
	@Autowired
	private ProductRepository proRepo;

	@Override
	public List<Catalog> getAllCatalog() {
		// TODO Auto-generated method stub
		return catRepo.findAll();
	}

	@Override
	public Catalog addCatalog(Catalog cat) {
		// TODO Auto-generated method stub
		return catRepo.save(cat);
	}

	@Override
	public Catalog getCatalogById(int catId) {
		// TODO Auto-generated method stub
		return catRepo.findById(catId).get();
	}

	@Override
	public Catalog updateCatalog(int catId, Catalog cat) {
		// TODO Auto-generated method stub
		Catalog catUpdate = catRepo.findById(catId).get();
		catUpdate.setCatalogName(cat.getCatalogName());
		catUpdate.setCatalogParentId(cat.getCatalogParentId());
		catUpdate.setCatalogImg(cat.getCatalogImg());
		catUpdate.setCatalogDescription(cat.getCatalogDescription());
		catUpdate.setCatalogStatus(cat.isCatalogStatus());
		catUpdate.setCatalogIsHot(cat.isCatalogIsHot());
		catUpdate.setCatalogType(cat.isCatalogType());
		catUpdate.setProducts(cat.getProducts());
		return catRepo.save(catUpdate);
	}

	@Override
	public String deleteCatalog(int catId) {
		// TODO Auto-generated method stub
		Catalog cat = catRepo.findById(catId).get();
		for(Product product: cat.getProducts()) {
			if(product.getCatalog().getCatalogId() == cat.getCatalogId()) {
				proRepo.delete(product);
			}
		}
		cat.setProducts(null);
		catRepo.delete(cat);
		return "Deleted!";
	}

	@Override
	public String deleteAllCatalog() {
		// TODO Auto-generated method stub
		catRepo.deleteAll();
		return "Delete All!";
	}

}
