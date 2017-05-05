package com.f2x.service;

import com.f2x.domain.User;

public interface UserService {

	User findByUsername(String username);
	User findByEmail(String email);
	
	boolean checkUserExists(String username, String email);
	boolean checkUsernameExists(String username);
	boolean checkEmailExists(String email);
	
	void saveUser (User user);
}
