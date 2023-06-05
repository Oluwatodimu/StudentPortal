package io.todimu.practice.studentportal.exception;

public class CourseAlreadyRegisteredException extends RuntimeException {

    public CourseAlreadyRegisteredException(String message) {
        super(message);
    }
}
