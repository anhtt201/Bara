package tran.tuananh.service;

import java.util.List;

import tran.tuananh.model.Catalog;
import tran.tuananh.model.User;

public interface UserService {

	public User getUserById(int id);

	public User getUserByName(String name);

	public User getUserByEmail(String email);

	public User updateUser(int userId, User user);

	public User findByPhone(String phone);
}
