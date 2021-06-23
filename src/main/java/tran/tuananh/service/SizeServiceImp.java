package tran.tuananh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tran.tuananh.model.Size;
import tran.tuananh.repository.SizeRepository;

@Service
public class SizeServiceImp implements SizeService {
	@Autowired
	private SizeRepository sizeRepo;

	@Override
	public List<Size> getAllSize() {
		// TODO Auto-generated method stub
		return sizeRepo.findAll();
	}

	@Override
	public Size addSize(Size size) {
		// TODO Auto-generated method stub
		return sizeRepo.save(size);
	}

	@Override
	public Size getSizeById(int sizeId) {
		// TODO Auto-generated method stub
		return sizeRepo.findById(sizeId).get();
	}

	@Override
	public Size updateSize(int sizeId, Size size) {
		// TODO Auto-generated method stub
		Size sizeUpdate = sizeRepo.findById(sizeId).get();
		sizeUpdate.setSizeName(size.getSizeName());
		sizeUpdate.setSizeStatus(size.isSizeStatus());
//		sizeUpdate.setProduct(size.getProduct());
//		SizeUpdate.setOrderDetails(Size.getOrderDetails());
		return sizeRepo.save(sizeUpdate);
	}

	@Override
	public String deleteSize(int sizeId) {
		// TODO Auto-generated method stub
		Size size = sizeRepo.findById(sizeId).get();
		sizeRepo.delete(size);
		return "Deleted!";
	}
}
