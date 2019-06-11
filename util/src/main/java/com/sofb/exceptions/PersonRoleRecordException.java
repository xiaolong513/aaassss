package com.sofb.exceptions;

public class PersonRoleRecordException extends RuntimeException {
    private String msg;

    public PersonRoleRecordException(String msg) {
        this(msg, null);
    }

    public PersonRoleRecordException(String msg, Exception e) {
        super(msg, e);
        this.msg = msg;
    }
}
