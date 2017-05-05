package com.f2x.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.f2x.domain.User;
import com.f2x.service.UserService;

@Controller
public class HomeController {

	@Autowired
	private UserService userService;
	
	
	@RequestMapping("/")
	public String home(){
		return "redirect:/index.html"; //this was redirect:/index and issue 505 appear, i do 
		//not why. so after of reading a little bit i realized that redirect must be implemented 
		//when is a path or complete URL, this was a mistake of coding. Resolved.
	}
	
	@RequestMapping("/index")
	public String index(){
		return "index";
	}
	
	@RequestMapping(value="/signup", method = RequestMethod.GET)
	public String singup(Model model){
		
		User user = new User();
		model.addAttribute("user", user);
		
		return "signup";
	}
	
	@RequestMapping(value="/signup", method = RequestMethod.POST)
	public String singupPost(@ModelAttribute("user") User user,  Model model){
		
		if(userService.checkUserExists(user.getUsername(), user.getEmail())){
			
			if(userService.checkEmailExists(user.getEmail())){
				model.addAttribute("emailExists", true);
			}
			if(userService.checkUsernameExists(user.getUsername())){
				model.addAttribute("userNameExists", true);
			}
		
			return "signup";
			
		}else{
			userService.saveUser(user);
			return "redirect:/";
		}
		
	
	}

}
