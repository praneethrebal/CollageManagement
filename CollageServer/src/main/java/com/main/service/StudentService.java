package com.main.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.main.DTO.StudentDTO;
import com.main.credientials.User;
import com.main.credientials.repo.UserRepo;
import com.main.entity.Department;
import com.main.entity.Grade;
import com.main.entity.Student;
import com.main.enums.UserRole;
import com.main.repo.GradeRepo;
import com.main.repo.StudentRepo;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentService {

	private final StudentRepo studentRepo;
	private final UserRepo userRepo;
	private final GradeRepo gradeRepo;
	private final DepartmentService departmentService;

	@Transactional
	public Student addStudent(Student student) {
		
		 if (student.getRoll_no() == null || student.getRoll_no().isEmpty()) {
		        throw new IllegalArgumentException("Roll_no must not be null or empty");
		    }
		Department dep=departmentService.findByCode(student.getDepartment().getDepertamentCode());
		if (dep==null) {
		    throw new IllegalArgumentException("Department or department code cannot be null.");
		}
		student.setDepartment(dep);
		studentRepo.save(student);
		User u=new User();
		u.setName(student.getName());
		u.setRoll_no(student.getRoll_no());
		u.setRole(UserRole.USER);
		u.setPassword(student.getRoll_no());
		userRepo.save(u);
		Grade grade=new Grade();
		grade.setStudent(student);
		grade.setGrade("A+");
		gradeRepo.save(grade);
		
		return student;
	}

	public StudentDTO getStudentByRollNo(String roll_no) {

		Student student=studentRepo.findByRollNo(roll_no);
		return StudentDtoConvert(student);
	}
	public List<StudentDTO> getAllStudents() {
		return studentRepo
					.findAll()
						.stream()
						.map(this::StudentDtoConvert)
						.collect(Collectors.toList());
	}
	
	public List<StudentDTO> findByBranchCode(String branchCode) {
		
		 return studentRepo
		 		.findByBranchCode(branchCode)
		 		.stream()
		 		.map(this::StudentDtoConvert)
		 		.collect(Collectors.toList());
	}
	private StudentDTO StudentDtoConvert(Student student) {
	    StudentDTO dto = new StudentDTO();
	    dto.setRoll_no(student.getRoll_no());
	    dto.setName(student.getName());
	    dto.setEmail(student.getEmail());
	    dto.setDepertamentCode(student.getDepartment().getDepertamentCode());
	    dto.setDepartmentName(student.getDepartment().getDepertamentName());
	    return dto;
	}





}
