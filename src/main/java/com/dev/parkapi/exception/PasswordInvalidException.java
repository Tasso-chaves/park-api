package com.dev.parkapi.exception;

public class PasswordInvalidException extends RuntimeException{
    
    public PasswordInvalidException(String message){
    super(message);                             
    }
}
