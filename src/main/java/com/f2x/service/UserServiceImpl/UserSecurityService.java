package com.f2x.service.UserServiceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.f2x.Dao.UserDao;
import com.f2x.domain.User;

@Service
public class UserSecurityService implements UserDetailsService {

	/**The application logger**/
	private static final Logger LOG = LoggerFactory.getLogger(UserSecurityService.class);
	
	@Autowired
	private UserDao userDao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
		User user = userDao.findByUsername(username);
		if(null == user){
			LOG.warn("username {} not found", username);
			throw new UsernameNotFoundException("Username" + username + "not found bitch");
		}
		return user;
	}
	
}
