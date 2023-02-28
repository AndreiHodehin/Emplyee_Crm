package com.hodehin.employeeapp.exception;

public class EmployeeDoThisTaskException extends RuntimeException {
    private EmployeeDoThisTaskException() {
        super();
    }
    public EmployeeDoThisTaskException(String msg) {
        super(msg);
    }
}
