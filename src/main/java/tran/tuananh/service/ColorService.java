package tran.tuananh.service;

import java.util.List;

import tran.tuananh.model.Color;

public interface ColorService {
	public List<Color> getAllColor();

	public Color addColor(Color color);

	public Color getColorById(int colorId);

	public Color updateColor(int colorId, Color color);

	public String deleteColor(int colorId);

}
