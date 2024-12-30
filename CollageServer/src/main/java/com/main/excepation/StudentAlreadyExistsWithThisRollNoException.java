package com.main.excepation;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.CONFLICT)
public class StudentAlreadyExistsWithThisRollNoException extends RuntimeException {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public StudentAlreadyExistsWithThisRollNoException(String msg)
	{
		super(msg);
	}

}
