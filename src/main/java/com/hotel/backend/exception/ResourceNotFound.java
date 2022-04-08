package com.hotel.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND)
public class ResourceNotFound extends RuntimeException {

	public static final long	SerialVersionUID	=	1L;
	
	public	ResourceNotFound(String	msg) {
		System.out.println("Error..."+msg);
	}
	
}
