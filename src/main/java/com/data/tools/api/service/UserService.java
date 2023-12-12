package com.data.tools.api.service;

import com.data.tools.api.exceptions.Exceptions;
import com.data.tools.api.exceptions.GlobalException;
import com.data.tools.api.repository.UserRepository;
import com.data.tools.api.util.UserHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.data.tools.api.entity.User;
import com.data.tools.api.entity.UserModel;

@Service
public class UserService {
	
	@Autowired
	private PasswordEncoder bcryptEncoder;
	
	@Autowired
	private UserRepository userRepository;

	public User createUser(UserModel user) {
		if (userRepository.existsByEmail(user.getEmail())) {
			throw GlobalException.throwEx(Exceptions.USER_ALREADY_EXIST, "User is already register with email:"+user.getEmail());
		}
		User newUser = new User();
		BeanUtils.copyProperties(user, newUser);
		newUser.setPassword(bcryptEncoder.encode(newUser.getPassword()));
		return userRepository.save(newUser);
	}


	public User updateUser(UserModel user) {
		User existingUser = UserHelper.getUser();
		existingUser.setName(user.getName() != null ? user.getName() : existingUser.getName());
		existingUser.setEmail(user.getEmail() != null ? user.getEmail() : existingUser.getEmail());
		existingUser.setPassword(user.getPassword() != null ? bcryptEncoder.encode(user.getPassword()) : existingUser.getPassword());
		existingUser.setAge(user.getAge() != null ? user.getAge() : existingUser.getAge());
		return userRepository.save(existingUser);
	}

	public void deleteUser() {
		User existingUser = UserHelper.getUser();
		userRepository.delete(existingUser);
	}



}

























