package com.main.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.main.DTO.GradeDTO;
import com.main.entity.Grade;
import com.main.entity.Student;
import com.main.repo.GradeRepo;
import com.main.repo.StudentRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GradeService {
	private final GradeRepo gradeRepo;
	private final StudentRepo studentRepo;
	public GradeDTO getGrade(String roll_no) {
		Grade grade= gradeRepo.findByRollNo(roll_no);
		return GradeDtoConvert(grade);
	}

	public Grade addGrade(Grade grade) {
		Student student=studentRepo.findByRollNo(grade.getStudent().getRoll_no());
		grade.setStudent(student);
		return gradeRepo.save(grade);
	}

	public List<GradeDTO> getAllGrades() {

		return gradeRepo
					.findAll()
					.stream()
					.map(this::GradeDtoConvert)
					.collect(Collectors.toList());
	}

	private GradeDTO GradeDtoConvert(Grade grade) {
		GradeDTO dto=new GradeDTO();
		dto.setName(grade.getStudent().getName());
		dto.setRollNo(grade.getStudent().getRoll_no());
		dto.setDepertamentCode(grade.getStudent().getDepartment().getDepertamentCode());
		dto.setGrade(grade.getGrade());
		return dto;
	}
}
