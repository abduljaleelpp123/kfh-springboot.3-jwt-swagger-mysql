package com.abdul.kfh.api.ctrl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abdul.kfh.AppConst;
import com.abdul.kfh.api.request.LoginRequest;
import com.abdul.kfh.api.request.SignupRequest;
import com.abdul.kfh.api.response.JwtResponse;
import com.abdul.kfh.api.response.MessageResponse;
import com.abdul.kfh.api.services.UserDetailsImpl;
import com.abdul.kfh.entity.ERole;
import com.abdul.kfh.entity.Role;
import com.abdul.kfh.entity.User;
import com.abdul.kfh.repository.RoleRepository;
import com.abdul.kfh.repository.UserRepository;
import com.abdul.kfh.security.jwt.JwtUtils;

import jakarta.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class AuthCtrl implements AppConst{
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	/**
	 * @param signUpRequest
	 * @return
	 */
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		
		MessageResponse res = new MessageResponse();
		
		if (userRepository.existsByUsername(signUpRequest.getEmail())) {
			
			res.setErrorCode(30);
			res.setMessage("Error: Username/Email is already taken!");
			return ResponseEntity.badRequest().body(res);
		}

		User user = new User(signUpRequest.getEmail(), encoder.encode(signUpRequest.getPassword()),
				signUpRequest.getStatus(), signUpRequest.getGender(), signUpRequest.getName());

		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Role userRole = roleRepository.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin": {
					Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);

					break;
				}

				default: {
					Role userRole = roleRepository.findByName(ERole.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);
				}
				}
			});
		}

		user.setRoles(roles);
		userRepository.save(user);
		res.setMessage("User registered successfully!");
		res.setErrorCode(ERRORCODE_SUCCESS);
		return ResponseEntity.ok(res);
	}

	/**
	 * @param loginRequest
	 * @return
	 */
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(),
				userDetails.getName(), userDetails.getGender(), userDetails.getStatus(), roles));
	}

}
