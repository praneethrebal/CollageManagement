package com.main.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.main.credientials.User;
import com.main.principal.UserPricipal;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {
	private final com.main.credientials.repo.UserRepo userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user=userRepo.findByRollNo(username);
		if(user == null)
		{
			throw new UsernameNotFoundException(username+"Not in DB");
		}
		return new UserPricipal(user);
	}

}
