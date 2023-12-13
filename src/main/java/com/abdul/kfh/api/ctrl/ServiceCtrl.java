package com.abdul.kfh.api.ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abdul.kfh.api.request.UserFilter;
import com.abdul.kfh.api.services.UserService;

import jakarta.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/service")
public class ServiceCtrl {
	

	@Autowired
	UserService userService;

	
	
	
	

	/**
	 * @return
	 */
	@GetMapping("/users")
	public ResponseEntity<?> getUsers() {

		return ResponseEntity.ok(userService.getUsersBy(null));
	}

	/** get all posts available in database.
	 * @return <code> List  </code> of the post object
	 */
	
	
	@PostMapping("/users")
	public ResponseEntity<?> getUsersBy(
			@Valid @RequestBody UserFilter userfilter) {
		
		System.out.println(userfilter.toString());
		return ResponseEntity.ok(userService.getUsersBy(userfilter));

	}
	
	
	
	

}
