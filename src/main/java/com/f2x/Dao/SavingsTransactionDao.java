package com.f2x.Dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.f2x.domain.SavingTransaction;

public interface SavingsTransactionDao extends CrudRepository<SavingTransaction, Long>{

	List<SavingTransaction> findAll();
	
}
