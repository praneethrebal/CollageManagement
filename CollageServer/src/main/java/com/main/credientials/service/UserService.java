package com.main.credientials.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.main.credientials.User;
import com.main.credientials.repo.UserRepo;
import com.main.excepation.SamePasswordException;
import com.main.excepation.UserNotFoundException;
import com.main.security.service.JwtService;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class UserService {
	private final JwtService jwtservice;
	private final AuthenticationManager authenticationManager;
	private final UserRepo userRepo;
	
	public User findUser(String username,String password) {
		User user=userRepo.findByRoll_no(username);
		  if (user == null ) {
			  throw new UserNotFoundException("not found");
	        }
		  if( !user.getPassword().equals(password) || !user.getRoll_no().equals(username) )
		  {
			  Map<String,String> err=new HashMap<>();
			  err.put("error", "Details not match");
			  throw new  UserNotFoundException("not found");
		  }

		return userRepo.findByRoll_no(username);
	}
	
	
	
	public String verify(User user) {
		Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getRoll_no(), user.getPassword()));
		if (authentication != null)
		{
			return jwtservice.generateToken(user);
		}
		return "fail";

	}
	
	
	public User updatePassword(String user,String newPassword) {

		User u1= userRepo.findByRoll_no(user);
		if(u1.getPassword().equals(user))
		{
			throw new SamePasswordException(newPassword+"Old password and new Password cannot be same");
		}
		u1.setPassword(newPassword);

		return userRepo.save(u1);
	}

}
