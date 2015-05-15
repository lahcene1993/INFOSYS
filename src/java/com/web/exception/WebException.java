package com.web.exception;

public class WebException extends java.lang.Error {

    private int code;

    private String message;

    @Override
    public String getMessage() {
        return  "dfghjkksdfghjklgfhjk";
    }
    
    
    
    public WebException(int code) {
        this.code = code;
    }
    public WebException(int code, String message) {
        super(message);
        this.message=message;
        this.code = code;
    }
    public WebException(String message) {
        super(message);
        this.message=message;
    }

    public int getCode() {
        return code;
    }
}