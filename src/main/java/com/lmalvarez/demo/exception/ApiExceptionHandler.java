package com.lmalvarez.demo.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> validApiRequestException(MethodArgumentNotValidException ex) {
		List<String> details = new ArrayList<String>();   
        for(ObjectError error : ex.getBindingResult().getAllErrors()) {
            details.add(error.getDefaultMessage());
        }
		ApiException apiException = new ApiException("Schema Validation Failed", details, ex, HttpStatus.BAD_REQUEST); 
        return new ResponseEntity<>(apiException, HttpStatus.BAD_REQUEST);
    }
	
//	@ExceptionHandler(DateTimeParseException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public ResponseEntity<Object> validDateApiRequestException(DateTimeParseException ex) {
//		List<String> details = new ArrayList<String>();   
//        details.add("Invalid Date Format");
//		ApiException apiException = new ApiException("Schema Validation Failed", details, ex, HttpStatus.BAD_REQUEST); 
//        return new ResponseEntity<>(apiException, HttpStatus.BAD_REQUEST);
//    }
//	
	@ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> notFoundException(NotFoundException ex) {
		List<String> details = new ArrayList<String>();   
        details.add(ex.getMessage());
		ApiException apiException = new ApiException("Data Validation Failed", details, ex, HttpStatus.NOT_FOUND); 
        return new ResponseEntity<>(apiException, HttpStatus.NOT_FOUND);
    }
	
	@ExceptionHandler(ConflictException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<Object> conflictException(ConflictException ex) {
		List<String> details = new ArrayList<String>();   
        details.add(ex.getMessage());
		ApiException apiException = new ApiException("Data Validation Failed", details, ex, HttpStatus.CONFLICT); 
        return new ResponseEntity<>(apiException, HttpStatus.CONFLICT);
    }
	
	@ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Object> genericException(Exception ex) {
		List<String> details = new ArrayList<String>();   
        details.add(ex.getMessage());
		ApiException apiException = new ApiException("Internal Error", details, ex, HttpStatus.INTERNAL_SERVER_ERROR); 
        return new ResponseEntity<>(apiException, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}