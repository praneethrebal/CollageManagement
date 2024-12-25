package com.main.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.main.entity.Student;

@Repository
public interface StudentRepo extends JpaRepository<Student, Long> {
	@Query("select s from Student s where s.Roll_no=:roll_no")
	Student findByRollNo(String roll_no);
	@Query("select s from Student s where s.department.depertamentCode=:branchCode")
	List<Student> findByBranchCode(String branchCode);

}
