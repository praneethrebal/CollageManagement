package com.main.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.main.DTO.ResponseDTO;
import com.main.DTO.StudentDTO;
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
	public ResponseEntity<ResponseDTO> updatePassword(@RequestBody HelperDTO newPassword)
	{
		String rollno=((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
		 userService.updatePassword(rollno,newPassword.getPassword());
		 return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(HttpStatus.OK,"Password Updated Sucessfully" ));
	}


	@GetMapping("/test")
	public String test()
	{
		return "hi";
	}

	@GetMapping("/getStudent")
	public ResponseEntity<StudentDTO> getStudent(@RequestParam(required = false) String RollNo)
	{
		UserDetails de=(UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String roll_no=de.getUsername();
		boolean isAdmin=de.getAuthorities().stream().anyMatch(authrotie -> authrotie.getAuthority().equals("ROLE_ADMIN"));
		if(isAdmin && RollNo != null)
		{
			return ResponseEntity.status(HttpStatus.OK).body(studentService.getStudentByRollNo(RollNo));
		}
		return ResponseEntity.status(HttpStatus.OK).body(studentService.getStudentByRollNo(roll_no)) ;
	}
	@GetMapping("getGrade")
	public GradeDTO getGrades()
	{
		
		UserDetails details=(UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String roll_no=details.getUsername();
		return gradeService.getGrade(roll_no);
	}


}
