package com.crm.payload;

import java.util.Date;

public class ErrorDetails {

    private Date date;
    private String message;
    private String request;//create constructorand getter
    public ErrorDetails(Date date, String message, String request) {
        this.date = date;
        this.message = message;
        this.request = request;
    }//when we create objects we can supply value to it

    public Date getDate() {
        return date;
    }

    public String getMessage() {
        return message;
    }

    public String getRequest() {
        return request;
    }
}
