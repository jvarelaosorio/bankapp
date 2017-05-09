package com.f2x.Dao;

import org.springframework.data.repository.CrudRepository;

import com.f2x.domain.security.Role;

public interface RoleDao extends CrudRepository<Role, Integer>{

	Role findByName(String name);
	
}
