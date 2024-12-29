package com.main.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name="Roll_no",nullable = false,unique = true)
	@JsonProperty("Roll_no")
	private String Roll_no;
	@Column(name = "Std_Name",nullable = false)
	private String name;
	@Column(name = "Std_Email")
	private String email;
	@ManyToOne
	@JoinColumn(name="department_Code", nullable = false)
	private Department department;



}
