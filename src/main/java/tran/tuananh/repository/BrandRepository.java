package tran.tuananh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tran.tuananh.model.Brand;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Integer>{

}
