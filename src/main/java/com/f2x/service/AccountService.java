package com.f2x.service;

import com.f2x.domain.PrimaryAccount;
import com.f2x.domain.SavingsAccount;

public interface AccountService {

	PrimaryAccount createPrimaryAccount();
	SavingsAccount createSavingsAccount();
	
}
