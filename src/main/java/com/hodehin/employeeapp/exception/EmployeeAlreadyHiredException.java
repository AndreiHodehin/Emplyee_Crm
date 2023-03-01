package com.hodehin.employeeapp.exception;

public class EmployeeAlreadyHiredException extends RuntimeException {
    public EmployeeAlreadyHiredException() {
        super();
    }
    public EmployeeAlreadyHiredException(String employee_hired_earlier) {
        super(employee_hired_earlier);
    }
}
