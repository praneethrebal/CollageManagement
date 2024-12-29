package com.main.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.main.credientials.User;
import com.main.credientials.repo.UserRepo;
import com.main.excepation.UserNotFoundExcepation;
import com.main.principal.UserPricipal;

import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class MyUserDetailsService implements UserDetailsService {
	private final UserRepo userRepo;
	@Override
	public UserDetails loadUserByUsername(String username) throws UserNotFoundExcepation {
		User user=userRepo.findByRoll_no(username);
		if(user == null)
		{
			throw new UserNotFoundExcepation(username+"Not in DB");
		}
		return new UserPricipal(user);
	}
}
