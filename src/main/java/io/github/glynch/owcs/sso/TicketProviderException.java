package io.github.glynch.owcs.sso;

public class TicketProviderException extends SSOException {

    private final String service;
    private final String tgt;

    public TicketProviderException(String service, String tgt, Throwable cause) {
        super(String.format("Failed to get ticket for service %s with tgt  %s", service, tgt), cause);
        this.service = service;
        this.tgt = tgt;
    }

    public String getService() {
        return service;
    }

    public String getTgt() {
        return tgt;
    }

}
