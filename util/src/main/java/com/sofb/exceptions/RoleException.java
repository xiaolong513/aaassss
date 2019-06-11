package com.sofb.exceptions;

public class RoleException extends RuntimeException {
    private String msg;

    public RoleException(String msg) {
        this(msg, null);
    }

    public RoleException(String msg, Exception e) {
        super(msg, e);
        this.msg = msg;
    }
}
