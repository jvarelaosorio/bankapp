package com.f2x.Dao;

import org.springframework.data.repository.CrudRepository;

import com.f2x.domain.PrimaryAccount;


public interface PrimaryAccountDao extends CrudRepository<PrimaryAccount, Long>{

	PrimaryAccount findByAccountNumber(int accountNumber);
	
}
