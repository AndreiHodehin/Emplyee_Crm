package com.hodehin.employeeapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(EmployeeNotHiredException.class)
    public ResponseEntity<Object> employeeNotHired(EmployeeNotHiredException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(DepartmentNotFoundException.class)
    public ResponseEntity<Object> employeeNotHired(DepartmentNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(EmployeeDoThisTaskException.class)
    public ResponseEntity<Object> employeeNotHired(EmployeeDoThisTaskException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.ALREADY_REPORTED);
    }
    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<Object> employeeNotHired(EmployeeNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<Object> employeeNotHired(TaskNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmployeeAlreadyHiredException.class)
    public ResponseEntity<Object> employeeAlreadyHired(EmployeeAlreadyHiredException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
}
