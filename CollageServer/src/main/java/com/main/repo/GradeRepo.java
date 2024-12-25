package com.main.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.main.entity.Grade;

@Repository
public interface GradeRepo extends JpaRepository<Grade, Long>{

	@Query("select g from Grade g where g.student.Roll_no=:Roll_no")
	Grade findByRollNo(String Roll_no);

}
