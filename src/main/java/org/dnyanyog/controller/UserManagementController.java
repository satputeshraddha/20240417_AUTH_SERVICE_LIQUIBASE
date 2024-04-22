package org.dnyanyog.controller;

import java.util.List;

import org.dnyanyog.dto.AddUserRequest;
import org.dnyanyog.dto.AddUserResponse;
import org.dnyanyog.entity.Users;
import org.dnyanyog.service.UserManagementService;
import org.dnyanyog.service.UserManagementServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserManagementController 
{
	@Autowired
	UserManagementService userService;

	@PostMapping(path = "/api/v1/auth/user", consumes = { "application/json", "application/xml" }, produces = {
			"application/json", "application/xml" })
	public AddUserResponse addUpdateUser(@RequestBody AddUserRequest userRequest) throws Exception {

		return userService.addUpdateUser(userRequest).orElse(new AddUserResponse());
	}

	@GetMapping(path = "/api/v1/auth/user/{userId}")
	public AddUserResponse getSingleUser(@PathVariable Long userId) throws Exception {
		return userService.getSingleUser(userId);
	}

	@GetMapping(path = "/api/v1/auth/user")
	public List<Users> getAllUser() {
		return userService.getAllUser();
	}

	@GetMapping(path = "/api/v1/auth/user_ids")
	public List<Long> getAllUserIds() {
		return userService.getAllUserIds();
	}
	
	@PostMapping(path = "/auth/v1/user/update/{userID}")
	public AddUserResponse updateUser(@PathVariable Long userID, @RequestBody Users request) throws Exception {
		return userService.updateUser(userID, request);

	}
	

}
