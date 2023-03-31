package com.example.system.exception;

public class NoDataFoundException extends RuntimeException {

    /**
     * コンストラクタ.
     */
    public NoDataFoundException(String message) {
        super(message);
    }
}