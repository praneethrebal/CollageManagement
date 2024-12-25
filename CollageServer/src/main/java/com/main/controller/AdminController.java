package com.main.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.main.DTO.GradeDTO;
import com.main.DTO.StudentDTO;
import com.main.entity.Department;
import com.main.entity.Grade;
import com.main.entity.Student;
import com.main.service.DepartmentService;
import com.main.service.GradeService;
import com.main.service.StudentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("admin")
@RequiredArgsConstructor
public class AdminController {
	private final DepartmentService departmentService;
	private final StudentService studentService;
	private final GradeService gradeService;
	@GetMapping("test")
	public String test()
	{
		return "admin";
	}

	@PostMapping("addDepartment")
	public Department addDepartment(@RequestBody Department department)
	{
		departmentService.addDepartment(department);
		return department;
	}
	@GetMapping("getAllDepartments")
	public List<Department> getAllDepartments()
	{
		return departmentService.getAllDepartments();
	}

	@PostMapping("addStudent")
	public Student addStudent(@RequestBody Student student)
	{
		return studentService.addStudent(student);
	}
	@GetMapping("getAllStudents")
	public List<StudentDTO> getAllStudents()
	{
		return studentService.getAllStudents();
	}
	@PostMapping("addGrade")
	public Grade addGrade(@RequestBody Grade grade)
	{

		return gradeService.addGrade(grade);
	}
	@GetMapping("getAllGrades")
	public List<GradeDTO> getAllGrades()
	{
		return gradeService.getAllGrades();
	}
	@GetMapping("/GetStudentsByBranch/{branchCode}")
	public List<StudentDTO> GetStudentsByBranch(@PathVariable String branchCode)
	{
		
		return studentService.findByBranchCode(branchCode);
	}

}
