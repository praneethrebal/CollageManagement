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
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ErrorResponseDTO> handleUserNotFoundException(UserNotFoundException excepation,WebRequest webRequest)
	{
		ErrorResponseDTO errorResponse = new ErrorResponseDTO(
									webRequest.getDescription(false)
									, HttpStatus.NOT_FOUND
									, excepation.getMessage()
									, LocalDateTime.now());
		return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(SamePasswordException.class)
	public ResponseEntity<ErrorResponseDTO> handleSamePasswordException(SamePasswordException excepation,WebRequest webRequest)
	{
		ErrorResponseDTO errResponse = new ErrorResponseDTO(
									webRequest.getDescription(false)
									, HttpStatus.BAD_REQUEST
									, excepation.getMessage()
									, LocalDateTime.now());
		return new ResponseEntity<>(errResponse,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(StudentAlreadyExistsWithThisRollNoException.class)
	public ResponseEntity<ErrorResponseDTO> handeleStudentAlreadyExistsWithThisRollNoException(StudentAlreadyExistsWithThisRollNoException excepation,WebRequest webRequest)
	{
		ErrorResponseDTO errResponse = new ErrorResponseDTO(
									webRequest.getDescription(false)
									, HttpStatus.CONFLICT
									, excepation.getMessage()
									, LocalDateTime.now());
		return new ResponseEntity<>(errResponse,HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(FieldCannotBeNullException.class)
	public ResponseEntity<ErrorResponseDTO> handleFieldCannotBeNullException(FieldCannotBeNullException excepation,WebRequest webRequest)
	{
		ErrorResponseDTO errResponse = new ErrorResponseDTO(
									webRequest.getDescription(false)
									, HttpStatus.BAD_REQUEST
									, excepation.getMessage()
									, LocalDateTime.now());
		return new ResponseEntity<>(errResponse,HttpStatus.BAD_REQUEST);
	}
	
}

