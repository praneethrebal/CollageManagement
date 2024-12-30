package com.main.excepation;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST)
public class SamePasswordException extends RuntimeException{

/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		public SamePasswordException(String msg)
		{
			super(msg);
		}
			

}
