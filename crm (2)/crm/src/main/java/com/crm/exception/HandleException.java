package com.crm.exception;

import com.crm.payload.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

////catch block this is global catch block spring boot sends exception here
@ControllerAdvice//becomes a global access such that wherever exception occurs in project it comes here
public class HandleException {

    @ExceptionHandler(Exception.class)
    //if resource not found exception occurs it directly comes here to this method
////Remember this code for interviews
    public ResponseEntity<ErrorDetails> globalException(
            Exception e,// can handle any exception this is exception class
            WebRequest  request  //gives ip address of client
//response entity coverts string to json and sends to postman always conert string to response entity use this only
    ) {
        ErrorDetails errorDetails = new ErrorDetails(
                new Date(),
                e.getMessage(),
                //request.getDescription(true)
                //if we dont want to ip address client details then instead of true type false
                request.getDescription(false)
                //we can directly find wgere error is using uri

        );//this gives more details about client

        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}