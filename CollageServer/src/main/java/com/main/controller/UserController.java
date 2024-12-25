package com.main.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.main.DTO.GradeDTO;
import com.main.DTO.HelperDTO;
import com.main.DTO.StudentDTO;
import com.main.credientials.User;
import com.main.credientials.service.UserService;
import com.main.service.GradeService;
import com.main.service.StudentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {
	private final StudentService studentService;
	private final UserService userService;
	private final GradeService gradeService;

	@PutMapping("updatePassword")
	public User updatePassword(@RequestBody HelperDTO newPassword)
	{
		String rollno=((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
		return userService.updatePassword(rollno,newPassword.getPassword());
	}


	@GetMapping("/test")
	public String test()
	{
		return "hi";
	}

	@GetMapping("/getStudent")
	public StudentDTO getStudent(@RequestParam(required = false) String RollNo)
	{
		UserDetails de=(UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String roll_no=de.getUsername();
		boolean isAdmin=de.getAuthorities().stream().anyMatch(authrotie -> authrotie.getAuthority().equals("ROLE_ADMIN"));
		
		if(isAdmin && RollNo != null)
		{
		
			return studentService.getStudentByRollNo(RollNo);
		}
		return studentService.getStudentByRollNo(roll_no);
		
	}
	@GetMapping("getGrade")
	public GradeDTO getGrades()
	{
		
		UserDetails details=(UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String roll_no=details.getUsername();
		return gradeService.getGrade(roll_no);
	}


}
