package com.dev.parkapi.exception;

public class EntityUserNotFoundException extends RuntimeException{
    
    public EntityUserNotFoundException(String message){
        super(message);
    }
}
