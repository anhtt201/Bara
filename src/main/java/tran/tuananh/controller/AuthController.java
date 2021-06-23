package tran.tuananh.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tran.tuananh.jwt.JwtUtils;
import tran.tuananh.model.ERole;
import tran.tuananh.model.Role;
import tran.tuananh.model.User;
import tran.tuananh.payload.request.LoginRequest;
import tran.tuananh.payload.request.SignupRequest;
import tran.tuananh.payload.response.JwtResponse;
import tran.tuananh.payload.response.MessageResponse;
import tran.tuananh.repository.RoleRepository;
import tran.tuananh.repository.UserRepository;
import tran.tuananh.service.UserDetailsImpl;
import tran.tuananh.service.UserService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserService userService;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	@GetMapping("/signup")
	public ResponseEntity<List<User>> getAllUser() {
		List<User> listUser = userRepository.findAll();
		return new ResponseEntity<List<User>>(listUser, HttpStatus.OK);
	}

	@GetMapping("/signup/{id}")
	public ResponseEntity<User> getUserById(@PathVariable("id") int id) {
		User user = userService.getUserById(id);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@GetMapping("/checkusername/{name}")
	public ResponseEntity<User> getUserByName(@PathVariable("name") String name) {
		User user = userService.getUserByName(name);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@GetMapping("/checkuseremail/{email}")
	public ResponseEntity<User> getUserByEmail(@PathVariable("email") String email) {
		User user = userService.getUserByEmail(email);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(),
				userDetails.getEmail(), userDetails.getPhone(), userDetails.getAddress(), userDetails.getAvatar(),
				userDetails.getBalance(), userDetails.getDob(), userDetails.isStatus(), roles));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
		}

		// Create new user's account
		User user = new User(signUpRequest.getUsername(), signUpRequest.getEmail(),
				encoder.encode(signUpRequest.getPassword()), signUpRequest.getPhone(), signUpRequest.getAddress(),
				signUpRequest.getAvatar(), signUpRequest.getBalance(), signUpRequest.getDob(),
				signUpRequest.isStatus());

		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Role userRole = roleRepository.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);

					break;
				case "mod":
					Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(modRole);

					break;
				default:
					Role userRole = roleRepository.findByName(ERole.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);
				}
			});
		}

		user.setRoles(roles);
		userRepository.save(user);

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
	
	@PutMapping("/signup/{id}")
	public ResponseEntity<User> updateUser(@PathVariable("id") int id, @RequestBody User user) {
		User u = userService.updateUser(id, user);
		return new ResponseEntity<User>(u, HttpStatus.OK);
	}


}
