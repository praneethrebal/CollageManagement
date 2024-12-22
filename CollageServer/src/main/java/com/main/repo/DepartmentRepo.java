package com.main.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.main.entity.Department;

@Repository
public interface DepartmentRepo extends JpaRepository<Department, Long>{

	@Query("select d from Department d where d.depertamentCode=:depertamentCode")
	Department findBycode(String depertamentCode);

}
