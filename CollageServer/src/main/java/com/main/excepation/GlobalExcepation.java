package com.main.excepation;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.main.DTO.ErrorResponseDTO;

@ControllerAdvice
public class GlobalExcepation {
	
	@ExceptionHandler(UserNotFoundExcepation.class)
	public ResponseEntity<ErrorResponseDTO> handleUserNotFoundExcepation(UserNotFoundExcepation excepation,WebRequest webRequest)
	{
		ErrorResponseDTO errorResponse = new ErrorResponseDTO(
									webRequest.getDescription(false)
									, HttpStatus.UNAUTHORIZED
									, excepation.getMessage()
									, LocalDateTime.now());
		return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(SamePasswordExcepation.class)
	public ResponseEntity<ErrorResponseDTO> handleSamePasswordExcepation(SamePasswordExcepation excepation,WebRequest webRequest)
	{
		ErrorResponseDTO errResponse = new ErrorResponseDTO(webRequest.getDescription(false)
												, HttpStatus.BAD_REQUEST
												, excepation.getMessage()
												, LocalDateTime.now());
		return new ResponseEntity<>(errResponse,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(StudentAleadyExistsWithThisRollNoExcepation.class)
	public ResponseEntity<ErrorResponseDTO> handeleStudentAleadyExistsWithThisRollNoExcepation(StudentAleadyExistsWithThisRollNoExcepation excepation,WebRequest webRequest)
	{
		ErrorResponseDTO errResponse = new ErrorResponseDTO(webRequest.getDescription(false)
																	, HttpStatus.CONFLICT
																	, excepation.getMessage()
																	, LocalDateTime.now());
		return new ResponseEntity<>(errResponse,HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(FeildCannotBeNullExcepation.class)
	public ResponseEntity<ErrorResponseDTO> handleFeildCannotBeNullExcepation(FeildCannotBeNullExcepation excepation,WebRequest webRequest)
	{
		ErrorResponseDTO errResponse = new ErrorResponseDTO(webRequest.getDescription(false)
																	, HttpStatus.BAD_REQUEST
																	, excepation.getMessage()
																	, LocalDateTime.now());
		return new ResponseEntity<>(errResponse,HttpStatus.BAD_REQUEST);
	}

}
