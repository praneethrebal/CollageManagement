package com.main.DTO;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseDTO {
	
	
	private HttpStatus statusCode;
	private String Message;

}
