package com.sofb.exceptions;

public class RoleResourceRecordException extends RuntimeException {
    private String msg;

    public RoleResourceRecordException(String msg) {
        this(msg, null);
    }

    public RoleResourceRecordException(String msg, Exception e) {
        super(msg, e);
        this.msg = msg;
    }
}
