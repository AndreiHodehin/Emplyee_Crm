package com.hodehin.employeeapp.exception;

public class EmployeeNotHiredException extends RuntimeException{

    public EmployeeNotHiredException() {
        super();
    }
    public EmployeeNotHiredException(String msg) {
        super(msg);
    }
}
