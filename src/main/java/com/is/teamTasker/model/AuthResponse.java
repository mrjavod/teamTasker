package com.is.teamTasker.model;

public class AuthResponse {

    private String username;

    private String access_token;

    private Long expiry;

    private String client_id;

    public AuthResponse(String username, String access_token, Long expiry, String client_id) {
        this.username = username;
        this.access_token = access_token;
        this.expiry = expiry;
        this.client_id = client_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public Long getExpiry() {
        return expiry;
    }

    public void setExpiry(Long expiry) {
        this.expiry = expiry;
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }
}
