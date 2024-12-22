package com.main.credientials.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.main.credientials.User;
import com.main.credientials.repo.UserRepo;
import com.main.security.service.JwtService;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class UserService {
	private final JwtService jwtservice;
	private final AuthenticationManager authenticationManager;
	private final UserRepo userRepo;
	public String verify(User user) {
		Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getRoll_no(), user.getPassword()));
		if (authentication != null)
		{
			return jwtservice.generateToken(user);
		}
		return "fail";

	}
	public User updatePassword(String user,String newPassword) {

		User u1= userRepo.findByRollNo(user);
		 if (u1 == null) {
		        throw new UsernameNotFoundException("User not found");
		    }

		u1.setPassword(newPassword);

		return userRepo.save(u1);
	}
	
	public User findUser(String username) {

		return userRepo.findByRollNo(username);
	}




}
