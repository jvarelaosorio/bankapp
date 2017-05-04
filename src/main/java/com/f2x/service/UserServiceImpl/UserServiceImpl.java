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
	
	public void save(User user) {
        userDao.save(user);
    }

    public User findByUsername(String username) {
        return userDao.findByUserName(username);
    }

    public User findByEmail(String email) {
        return userDao.findByEmail(email);
    }
    
    
}
