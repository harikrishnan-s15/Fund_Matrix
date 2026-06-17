package com.cog.fundmatrix.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cog.fundmatrix.dto.user.CreateUserRequest;
import com.cog.fundmatrix.dto.user.UpdateUserStatusRequest;
import com.cog.fundmatrix.dto.user.UserDto;
import com.cog.fundmatrix.service.UserService;

//
//@RestController
//@RequestMapping("/api/users")
public class UserController {
	
	private UserService userService;
	
	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}

	@PostMapping("/register")
	public ResponseEntity<UserDto> registerUser(@RequestBody CreateUserRequest dto)
	
	{
		UserDto response=userService.createUser(dto);
		
		return ResponseEntity.ok(response);
	}
	
	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> updateUser(@PathVariable UUID userId,@RequestBody CreateUserRequest dto)
	
	{
		UserDto response=userService.updateUser(userId,dto);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> findUser(@PathVariable UUID userId)
	{
		UserDto response=userService.findUser(userId);
		
		return ResponseEntity.ok(response);
	}
	
	@PutMapping("/{userId}/status")
	public ResponseEntity<UserDto> updateUserStatus(@PathVariable UUID userId,@RequestBody UpdateUserStatusRequest dto)
	
	{
		UserDto response=userService.updateUserStatus(userId,dto);
		
		return ResponseEntity.ok(response);
	}
	
//	@PutMapping("/{userId}/status")
//	public ResponseEntity<String> deleteUser(@PathVariable UUID id)
//	
//	{
//		userService.deleteUser(id);
//		
//		return ResponseEntity.ok("user deleted with id : "+id);
//	}
//	
//	@GetMapping("/")
//	public ResponseEntity<List<UserDto>> getAllUsers()
//	
//	{
//		List<UserDto> response=userService.getAllUser();
//		
//		return ResponseEntity.ok(response);
//	}
}
