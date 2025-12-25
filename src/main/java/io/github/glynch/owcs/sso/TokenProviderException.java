package io.github.glynch.owcs.sso;

public class TokenProviderException extends SSOException {

    private final String service;
    private final String username;
    private final String password;

    public TokenProviderException(String service, String username, String password, Throwable cause) {
        super(String.format("Failed to get token for service %s and username %s", service, username), cause);
        this.service = service;
        this.username = username;
        this.password = password;
    }

    public String getService() {
        return service;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

}
