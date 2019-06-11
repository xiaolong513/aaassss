package com.sofb.exceptions;

public class PersonException extends RuntimeException {
    private String msg;

    public PersonException(String msg) {
        this(msg, null);
    }

    public PersonException(String msg, Exception e) {
        super(msg, e);
        this.msg = msg;
    }
}
