package kw.kng.sms.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST)
public class SmsException extends RuntimeException
{
	private String message;

	public SmsException(String message) 
	{
		super(message);
	}
	
	

}
