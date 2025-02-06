package kw.kng.nad.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST)
public class NadException extends RuntimeException
{
	private String message;

	public NadException(String message) 
	{
		super(message);
	}
	
	

}
