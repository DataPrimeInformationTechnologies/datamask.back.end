package com.data.tools.api.service;

import com.data.tools.api.entity.User;
import com.data.tools.api.entity.UserModel;

public interface UserService {
	
	User createUser(UserModel user);
	
	User readUser();
	
	User updateUser(UserModel user);
	
	void deleteUser();
	
	User getLoggedInUser();
}
