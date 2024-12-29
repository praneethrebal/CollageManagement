package com.main.excepation;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST)
public class SamePasswordExcepation extends RuntimeException{

/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		public SamePasswordExcepation(String msg)
		{
			super(msg);
		}
			

}
