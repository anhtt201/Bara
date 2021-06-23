package tran.tuananh.service;

import java.util.List;

import tran.tuananh.model.Catalog;

public interface CatalogService {

	public List<Catalog> getAllCatalog();

	public Catalog addCatalog(Catalog cat);

	public Catalog getCatalogById(int catId);

	public Catalog updateCatalog(int catId, Catalog cat);

	public String deleteCatalog(int catId);

	public String deleteAllCatalog();
}
