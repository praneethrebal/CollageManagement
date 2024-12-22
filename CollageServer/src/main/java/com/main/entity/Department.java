package com.main.entity;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Department {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false,unique = true ,name="Depertament_Code" )
	private String depertamentCode;
	@Column(nullable = false,unique = true ,name="Depertament_Name" )
	private String depertamentName;

}
