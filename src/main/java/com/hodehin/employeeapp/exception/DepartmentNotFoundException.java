package com.hodehin.employeeapp.exception;

public class DepartmentNotFoundException extends RuntimeException {
    public DepartmentNotFoundException() {
        super();
    }

    public DepartmentNotFoundException(String msg) {
        super(msg);
    }
}
