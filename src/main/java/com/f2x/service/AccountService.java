package com.f2x.service;

import java.security.Principal;

import com.f2x.domain.PrimaryAccount;
import com.f2x.domain.SavingsAccount;

public interface AccountService {

	PrimaryAccount createPrimaryAccount();
	SavingsAccount createSavingsAccount();
	
	void deposit(String accountType, double amount, Principal principal);
	void withdraw(String accountType, double amount, Principal principal);
}
