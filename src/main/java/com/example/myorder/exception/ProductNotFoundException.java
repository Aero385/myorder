package com.example.myorder.exception;

public class ProductNotFoundException extends Throwable{
    public ProductNotFoundException(String message) {
        super(message);
    }
}
