package tran.tuananh.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tran.tuananh.model.User;
import tran.tuananh.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService, UserService {
	@Autowired
	UserRepository userRepository;

	@Autowired
	PasswordEncoder encoder;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

		return UserDetailsImpl.build(user);
	}

	@Override
	public User getUserById(int id) {
		// TODO Auto-generated method stub
		return userRepository.findById(id).get();
	}

	@Override
	public User updateUser(int userId, User user) {
		// TODO Auto-generated method stub
		User userUpdate = userRepository.findById(userId).get();
		userUpdate.setUsername(user.getUsername());
		userUpdate.setEmail(user.getEmail());
//		userUpdate.setPassword(encoder.encode(user.getPassword()));
		userUpdate.setAddress(user.getAddress());
		userUpdate.setAvatar(user.getAvatar());
		userUpdate.setPhone(user.getPhone());
		userUpdate.setBalance(user.getBalance());
		userUpdate.setDob(user.getDob());

		return userRepository.save(userUpdate);
	}

	@Override
	public User getUserByName(String name) {
		List<User> users = userRepository.findAll();
		for (User user : users) {
			if (user.getUsername().equals(name))
				return user;
		}
		return null;
	}

	@Override
	public User getUserByEmail(String email) {
		// TODO Auto-generated method stub
		List<User> users = userRepository.findAll();
		for (User user : users) {
			if (user.getEmail().equals(email))
				return user;
		}
		return null;
	}

	@Override
	public User findByPhone(String phone) {
		// TODO Auto-generated method stub
		return userRepository.findByPhone(phone).get();
	}

}
