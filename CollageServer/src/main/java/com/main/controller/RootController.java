package com.main.controller;


import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.main.DTO.LoginDTO;
import com.main.credientials.User;
import com.main.credientials.service.UserService;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RestController
@Data
@RequiredArgsConstructor
public class RootController {
	private final UserService userService;
	@PostMapping("/login")
	public ResponseEntity<Map<String,String>>  login(@RequestBody LoginDTO loginDTO)
	{
		User user=userService.findUser(loginDTO.getUsername());
		  if (user == null ) {
	            Map<String, String> errorResponse = new HashMap<>();
	            errorResponse.put("error", "User not found");
	            return ResponseEntity.status(404).body(errorResponse);
	        }
		  if( !user.getPassword().equals(loginDTO.getPassword()) || !user.getRoll_no().equals(loginDTO.getUsername()) )
		  {
			  Map<String,String> err=new HashMap<>();
			  err.put("error", "Details not match");
			  return ResponseEntity.status(401).body(err);
		  }
		String token= userService.verify(user);
		Map<String,String> response=new HashMap<>();
		response.put("username", user.getName());
		response.put("role", user.getRole().name());
		response.put("token", token);

		return ResponseEntity.ok(response);

	}


}
