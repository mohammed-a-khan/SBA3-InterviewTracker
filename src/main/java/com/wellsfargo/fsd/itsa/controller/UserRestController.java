package com.wellsfargo.fsd.itsa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wellsfargo.fsd.itsa.entity.Users;
import com.wellsfargo.fsd.itsa.exception.ItsException;
import com.wellsfargo.fsd.itsa.service.UserService;

@RestController
@RequestMapping("/user")
public class UserRestController {

	@Autowired
	private UserService userService;

	@GetMapping
	public ResponseEntity<List<Users>> getAllUser() throws ItsException {
		return new ResponseEntity<List<Users>>(userService.getAllUsers(), HttpStatus.OK);
	}
	

	@DeleteMapping("/{userId}")
	public ResponseEntity<Void> deleteItem(@PathVariable("userId") int userId) throws ItsException {
		ResponseEntity<Void> response = null;

		boolean isDeleted = userService.deleteUser(userId);

		if (isDeleted) {
			response = new ResponseEntity<>(HttpStatus.OK);
		} else {
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return response;
	}

	@PostMapping
	public ResponseEntity<Users> add(@RequestBody Users users) throws ItsException {
		return new ResponseEntity<Users>(userService.add(users), HttpStatus.OK);
	}

}
