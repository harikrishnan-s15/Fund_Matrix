
package com.cog.fundmatrix.service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cog.fundmatrix.domain.User;
import com.cog.fundmatrix.domain.enums.Role;
import com.cog.fundmatrix.domain.enums.UserStatus;
import com.cog.fundmatrix.dto.user.CreateUserRequest;
import com.cog.fundmatrix.dto.user.UpdateUserStatusRequest;
import com.cog.fundmatrix.dto.user.UserDto;
import com.cog.fundmatrix.exception.ResouceNotFoundException;
import com.cog.fundmatrix.repository.UserRepository;




@Service
public class UserService {

	UserRepository userRepo;
	
	PasswordEncoder encoder;
	
	public UserService(UserRepository userRepo, PasswordEncoder encoder) {
		super();
		this.userRepo = userRepo;
		this.encoder = encoder;
	}

	public UserDto mapToResponse(User saved)
	{
		UserDto response=new UserDto(saved.getUserId(),saved.getName(),saved.getEmail(),saved.getPhone(),saved.getRole(),saved.getStatus());
		return response;
	}
	
	public UserDto createUser(CreateUserRequest dto) {
		User user=new User();
		user.setName(dto.name());
		user.setEmail(dto.email());
		user.setRole(dto.role());
		user.setStatus(UserStatus.ACTIVE);
		String encryptedPassword=encoder.encode(dto.password());
		user.setPassword(encryptedPassword);
		User saved=userRepo.save(user);
		
		return mapToResponse(saved);
	}
	
	
	public UserDto updateUser(UUID userId,CreateUserRequest dto) {
		User user=userRepo.findById(userId).orElseThrow(()->new ResouceNotFoundException("user not found"));
		user.setName(dto.name());
		user.setEmail(dto.email());
		user.setRole(dto.role());
		User saved=userRepo.save(user);
		
		return mapToResponse(saved);
	}

	public UserDto updateUserStatus(UUID userId,UpdateUserStatusRequest dto)
	{
		User user=userRepo.findById(userId).orElseThrow(()->new ResouceNotFoundException("user not found"));
		user.setStatus(dto.status());
		User saved=userRepo.save(user);
		
		return mapToResponse(saved);
	}
	
	public void deleteUser(UUID id) {
		Optional<User> user=userRepo.findById(id);
		if(user.isPresent())
		{
			userRepo.deleteById(id);
			return;
		}
		throw new ResouceNotFoundException("User not found with id : "+id);
	}
	
	
	public UserDto findUser(UUID id) {
		Optional<User> user=userRepo.findById(id);
		if(user.isPresent())
		{
			return mapToResponse(user.get());
		}
		throw new ResouceNotFoundException("User not found with id : "+id);
	}
	
	
	public List<UserDto> getAllUser() {
		List<User> ls=userRepo.findAll();
		
		
		return ls.stream().map((user)->mapToResponse(user)).toList();
	}

}
