package com.f2x.service.UserServiceImpl;

import java.util.Set;

import org.springframework.transaction.annotation.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.f2x.Dao.RoleDao;
import com.f2x.Dao.UserDao;
import com.f2x.domain.User;
import com.f2x.domain.security.UserRole;
import com.f2x.service.AccountService;
import com.f2x.service.UserService;


@Service
@Transactional
public class UserServiceImpl implements UserService{

	private static final Logger LOG = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
    private AccountService accountService;
	
	public void saveUser(User user) {
        userDao.save(user);
    }

    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    public User findByEmail(String email) {
        return userDao.findByEmail(email);
    }
    
    
    public User createUser(User user,  Set<UserRole> userRoles){
    	User localUser = userDao.findByUsername(user.getUsername());
    	
    	if(localUser !=  null){
    		LOG.info("User with the name {} already exist. Nothing will be done.", user.getUsername());
    	}else{
    		String encryptedPassword = passwordEncoder.encode(user.getPassword());
    		user.setPassword(encryptedPassword);
    		
    		for(UserRole ur : userRoles){
    			roleDao.save(ur.getRole());
    		}
    		
    		user.getUserRoles().addAll(userRoles);
    		user.setPrimaryAccount(accountService.createPrimaryAccount());
    		user.setSavingAccount(accountService.createSavingsAccount());
    		
    		localUser = userDao.save(user);
    	}
    	
    	return localUser;
    }
    
    
    public boolean checkUserExists(String username, String email){
        if (checkUsernameExists(username) || checkEmailExists(username)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkUsernameExists(String username) {
        if (null != findByUsername(username)) {
            return true;
        }

        return false;
    }
    
    public boolean checkEmailExists(String email) {
        if (null != findByEmail(email)) {
            return true;
        }

        return false;
    }
    
    
}
