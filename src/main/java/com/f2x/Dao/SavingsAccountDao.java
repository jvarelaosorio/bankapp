package com.f2x.Dao;

import org.springframework.data.repository.CrudRepository;

import com.f2x.domain.SavingsAccount;

public interface SavingsAccountDao extends CrudRepository<SavingsAccount, Long>{

	SavingsAccount findByAccountNumber(int accountNumber);
	
}
