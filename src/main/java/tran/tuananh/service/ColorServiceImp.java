package tran.tuananh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tran.tuananh.model.Color;
import tran.tuananh.repository.ColorRepository;

@Service
public class ColorServiceImp implements ColorService {

	@Autowired
	private ColorRepository colorRepo;

	@Override
	public List<Color> getAllColor() {
		// TODO Auto-generated method stub
		return colorRepo.findAll();
	}

	@Override
	public Color addColor(Color color) {
		// TODO Auto-generated method stub
		return colorRepo.save(color);
	}

	@Override
	public Color getColorById(int colorId) {
		// TODO Auto-generated method stub
		return colorRepo.findById(colorId).get();
	}

	@Override
	public Color updateColor(int colorId, Color color) {
		// TODO Auto-generated method stub
		Color colorUpdate = colorRepo.findById(colorId).get();
		colorUpdate.setColorName(color.getColorName());
		colorUpdate.setColorStatus(color.isColorStatus());
//		colorUpdate.setProduct(color.getProduct());
//		colorUpdate.setOrderDetails(color.getOrderDetails());
		return colorRepo.save(colorUpdate);
	}

	@Override
	public String deleteColor(int colorId) {
		// TODO Auto-generated method stub
		Color color = colorRepo.findById(colorId).get();
		colorRepo.delete(color);
		return "Deleted!";
	}

}
