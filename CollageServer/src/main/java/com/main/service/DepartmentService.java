package com.main.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.main.entity.Department;
import com.main.repo.DepartmentRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DepartmentService {
	private final DepartmentRepo departmentRepo;

	public Department addDepartment(Department department) {
		return departmentRepo.save(department);

	}

	public List<Department> getAllDepartments() {
		return departmentRepo.findAll();

	}

	public Department findByCode(String depertamentCode) {

		return departmentRepo.findBycode(depertamentCode);
	}

}
