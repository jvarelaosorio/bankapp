package com.f2x.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMethod;

import com.f2x.domain.PrimaryAccount;
import com.f2x.domain.SavingsAccount;
import com.f2x.domain.User;
import com.f2x.service.AccountService;
import com.f2x.service.UserService;


@Controller
@RequestMapping("/account")
public class AccountController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AccountService accountService;
	
	@RequestMapping("/primaryAccount")
	public String primaryAccount(Model model, Principal principal) {
		User user = userService.findByUsername(principal.getName());
		PrimaryAccount primaryAccount = user.getPrimaryAccount();
		
		model.addAttribute("primaryAccount", primaryAccount);
		
		return "primaryAccount";
	}

	@RequestMapping("/savingsAccount")
    public String savingsAccount(Model model, Principal principal) {
		User user = userService.findByUsername(principal.getName());
		SavingsAccount savingsAccount = user.getSavingAccount();
		
		model.addAttribute("savingAccount", savingsAccount);
		
        return "savingsAccount";
    }
	
	@RequestMapping(value = "/deposit", method = RequestMethod.GET)
	public String deposit(Model model){
		model.addAttribute("accountType", "");
		model.addAttribute("amount", "");
		
		return "deposit";
	}
	
	@RequestMapping(value = "/deposit", method = RequestMethod.POST)
	public String depositPOST(@ModelAttribute("amount") String amount, @ModelAttribute("accountType") String accountType, Principal principal){
		
		accountService.deposit(accountType, Double.parseDouble(amount), principal);
		
		return "redirect:/userFront";
	}
    
	@RequestMapping(value = "/withdraw", method = RequestMethod.GET)
	public String withdraw(Model model){
		model.addAttribute("accountType", "");
		model.addAttribute("amount", "");
		
		return "withdraw";
	}
	
	@RequestMapping(value = "/withdraw", method = RequestMethod.POST)
	public String withdrawPOST(@ModelAttribute("amount") String amount, @ModelAttribute("accountType") String accountType, Principal principal){
		
		accountService.withdraw(accountType, Double.parseDouble(amount), principal);
		
		return "redirect:/userFront";
	}
	
	
}
