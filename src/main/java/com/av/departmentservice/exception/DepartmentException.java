package com.av.departmentservice.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DepartmentException extends Exception {
    public DepartmentException(String message) {
        super(message);
    }
}
