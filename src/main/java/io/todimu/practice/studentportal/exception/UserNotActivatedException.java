package io.todimu.practice.studentportal.exception;

public class UserNotActivatedException extends RuntimeException {

    public UserNotActivatedException() {
        super();
    }

    public UserNotActivatedException(String message) {
        super(message);
    }
}
