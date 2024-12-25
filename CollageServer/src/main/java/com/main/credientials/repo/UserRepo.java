package com.main.credientials.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.main.credientials.User;


@Repository
public interface UserRepo extends JpaRepository<User, Long>{
	@Query("select u from User u where u.Roll_no=:rollNo")
	User findByRollNo(String rollNo);

}
