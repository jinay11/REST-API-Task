package com.user.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.user.entity.User;
import com.user.exception.ResourceNotFoundException;
import com.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository repository;

	// create user
	public User saveUser(User user) {
		return repository.save(user);
	}

	// get all users
	public List<User> getAllUsers() {
		return repository.findAll();
	}

	// get user by id
	public User getUserById(Long id) {
		return repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User not found with id : " + id));
	}

	// update user
	public User updateUser(Long id, User user) {

		User existingUser = getUserById(id);
		existingUser.setName(user.getName());
		existingUser.setEmail(user.getEmail());
		existingUser.setPassword(user.getPassword());

		return repository.save(existingUser);

	}
	
	//delete user
	public void deleteUser(Long id) {
		User user = getUserById(id);
		repository.delete(user);
	}
}
