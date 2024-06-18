package com.ojt.first_be.exception;

public class ExcelInternalException extends RuntimeException {

    public ExcelInternalException(String message, Throwable e) {
        super(message, e);
    }
}
