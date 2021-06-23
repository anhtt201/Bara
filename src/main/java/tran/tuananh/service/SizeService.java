package tran.tuananh.service;

import java.util.List;

import tran.tuananh.model.Size;

public interface SizeService {

	public List<Size> getAllSize();

	public Size addSize(Size size);

	public Size getSizeById(int sizeId);

	public Size updateSize(int sizeId, Size size);

	public String deleteSize(int sizeId);

}
