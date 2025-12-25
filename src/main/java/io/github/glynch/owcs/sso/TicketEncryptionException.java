package io.github.glynch.owcs.sso;

public class TicketEncryptionException extends SSOException {

    private final String ticket;

    public TicketEncryptionException(String ticket, Throwable cause) {
        super(String.format("Failed to encrypt ticket %s", ticket), cause);
        this.ticket = ticket;
    }

    public String getTicket() {
        return ticket;
    }

}
