package com.crm.exception;

public class ResourceNotFound extends RuntimeException{
    public ResourceNotFound(String msg) {
        super(msg);//in coontroller layer (->ResourceNotFound call here
    }
}
