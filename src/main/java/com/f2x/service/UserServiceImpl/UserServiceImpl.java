package com.f2x.service.UserServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.f2x.Dao.UserDao;
import com.f2x.domain.User;
import com.f2x.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao userDao;
	
	public void saveUser(User user) {
        userDao.save(user);
    }

    public User findByUsername(String username) {
        return userDao.findByUserName(username);
    }

    public User findByEmail(String email) {
        return userDao.findByEmail(email);
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
