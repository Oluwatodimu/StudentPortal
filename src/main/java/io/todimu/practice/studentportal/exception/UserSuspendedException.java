package io.todimu.practice.studentportal.exception;

public class UserSuspendedException extends RuntimeException {

    public UserSuspendedException() {
        super();
    }

    public UserSuspendedException(String message) {
        super(message);
    }
}
