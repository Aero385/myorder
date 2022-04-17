package com.example.myorder.exception;

public class CustomerNotFoundException extends Throwable{
    public CustomerNotFoundException(String message) {
        super(message);
    }
}