package com.f2x.service;

import java.util.Set;

import com.f2x.domain.User;
import com.f2x.domain.security.UserRole;

public interface UserService {

	User findByUsername(String username);
	User findByEmail(String email);
	
	boolean checkUserExists(String username, String email);
	boolean checkUsernameExists(String username);
	boolean checkEmailExists(String email);
	
	void saveUser (User user);
	
	User createUser(User user, Set<UserRole> userRoles);
}
