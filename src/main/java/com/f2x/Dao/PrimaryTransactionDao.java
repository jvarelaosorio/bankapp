package com.f2x.Dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.f2x.domain.PrimaryTransaction;

public interface PrimaryTransactionDao extends CrudRepository<PrimaryTransaction, Long>{

	List<PrimaryTransaction> findAll();
	
}
