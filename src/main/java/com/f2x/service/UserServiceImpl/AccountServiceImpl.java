package com.f2x.service.UserServiceImpl;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.f2x.Dao.PrimaryAccountDao;
import com.f2x.Dao.SavingsAccountDao;
import com.f2x.domain.PrimaryAccount;
import com.f2x.domain.PrimaryTransaction;
import com.f2x.domain.SavingTransaction;
import com.f2x.domain.SavingsAccount;
import com.f2x.domain.User;
import com.f2x.service.AccountService;
import com.f2x.service.UserService;

@Service
public class AccountServiceImpl implements AccountService{

	private static int nextAccountNumber = 129090; 
	
	@Autowired
	private PrimaryAccountDao primaryAccountDao;
	
	@Autowired
	private SavingsAccountDao savingsAccountDao;
	
	@Autowired
	private UserService userService;
	
	public PrimaryAccount createPrimaryAccount() {
		PrimaryAccount primaryAccount = new PrimaryAccount();
		primaryAccount.setAccountBalance(new BigDecimal(0.0));
		primaryAccount.setAccountNumber(accountGen());
		
		primaryAccountDao.save(primaryAccount);
		return primaryAccountDao.findByAccountNumber(primaryAccount.getAccountNumber());
	}

	public SavingsAccount createSavingsAccount() {
		SavingsAccount savingsAccount = new SavingsAccount();
		savingsAccount.setAccountBalance(new BigDecimal(0.0));
		savingsAccount.setAccountNumber(accountGen());
		
		savingsAccountDao.save(savingsAccount);
		return savingsAccountDao.findByAccountNumber(savingsAccount.getAccountNumber());
		
	}

	public void deposit(String accountType, double amount, Principal principal) {
        User user = userService.findByUsername(principal.getName());

        if (accountType.equalsIgnoreCase("Primary")) {
            PrimaryAccount primaryAccount = user.getPrimaryAccount();
            primaryAccount.setAccountBalance(primaryAccount.getAccountBalance().add(new BigDecimal(amount)));
            primaryAccountDao.save(primaryAccount);

            Date date = new Date();

            PrimaryTransaction primaryTransaction = new PrimaryTransaction(date, "Deposit to Primary Account", "Account", "Finished", amount, primaryAccount.getAccountBalance());
            //transactionService.savePrimaryDepositTransaction(primaryTransaction);
            
        } else if (accountType.equalsIgnoreCase("Savings")) {
            SavingsAccount savingsAccount = user.getSavingAccount();
            savingsAccount.setAccountBalance(savingsAccount.getAccountBalance().add(new BigDecimal(amount)));
            savingsAccountDao.save(savingsAccount);

            Date date = new Date();
            SavingTransaction savingsTransaction = new SavingTransaction(date, "Deposit to savings Account", "Account", "Finished", amount, savingsAccount.getAccountBalance(), savingsAccount);
            //transactionService.saveSavingsDepositTransaction(savingsTransaction);
        }
    }
	
	public void withdraw(String accountType, double amount, Principal principal){
		User user = userService.findByUsername(principal.getName());
		
		if(accountType.equalsIgnoreCase("Primary")){
			PrimaryAccount primaryAccount = user.getPrimaryAccount();
			primaryAccount.setAccountBalance(primaryAccount.getAccountBalance().subtract(new BigDecimal(amount)));
			primaryAccountDao.save(primaryAccount);
			
		    Date date = new Date();
			PrimaryTransaction primaryTransaction = new PrimaryTransaction(date, "Withdraw to Primary Account", "Account", "Finished", amount, primaryAccount.getAccountBalance());
			
		} else if(accountType.equalsIgnoreCase("Savings")){
			
			SavingsAccount savingsAccount = user.getSavingAccount();
            savingsAccount.setAccountBalance(savingsAccount.getAccountBalance().subtract(new BigDecimal(amount)));
            savingsAccountDao.save(savingsAccount);

            Date date = new Date();
            SavingTransaction savingsTransaction = new SavingTransaction(date, "Deposit to savings Account", "Account", "Finished", amount, savingsAccount.getAccountBalance(), savingsAccount);
            //transactionService.saveSavingsDepositTransaction(savingsTransaction);
			
		}
	}
	
	
	private int accountGen() {
		
		return ++nextAccountNumber;
	}

	
	
}
