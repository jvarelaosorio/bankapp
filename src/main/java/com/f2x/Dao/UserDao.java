package com.f2x.Dao;

import org.springframework.data.repository.CrudRepository;

import com.f2x.domain.User;

public interface UserDao extends CrudRepository<User, Long>{

	User findByUsername(String username);
	User findByEmail(String email);
	
}
