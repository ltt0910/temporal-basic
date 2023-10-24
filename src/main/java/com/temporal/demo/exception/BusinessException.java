package com.temporal.demo.exception;

public class BusinessException extends Exception{
    private String mesage;

    public BusinessException(String mesage) {
        this.mesage = mesage;
    }
}
