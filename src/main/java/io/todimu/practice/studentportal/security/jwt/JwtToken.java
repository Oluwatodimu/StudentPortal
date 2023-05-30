package io.todimu.practice.studentportal.security.jwt;

public class JwtToken {

    private String authToken;

    public JwtToken(String authToken) {
        this.authToken = authToken;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }
}
