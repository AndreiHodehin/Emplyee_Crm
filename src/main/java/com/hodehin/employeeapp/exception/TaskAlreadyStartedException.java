package com.hodehin.employeeapp.exception;

public class TaskAlreadyStartedException extends RuntimeException {
    public TaskAlreadyStartedException() {
        super();
    }
    public TaskAlreadyStartedException(String msg) {
    super(msg);
    }
}
