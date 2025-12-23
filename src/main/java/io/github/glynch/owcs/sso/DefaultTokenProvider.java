package io.github.glynch.owcs.sso;

import java.util.Objects;

public class DefaultTokenProvider implements TokenProvider {

    private final TicketProvider ticketProvider;
    private final TicketEncryptor ticketEncryptor;

    DefaultTokenProvider(TicketProvider ticketProvider, TicketEncryptor ticketEncryptor) {
        this.ticketProvider = ticketProvider;
        this.ticketEncryptor = ticketEncryptor;
    }

    @Override
    public String getToken(String service, String username, String password) throws SSOException {
        Objects.requireNonNull(service, "service cannot be empty or null");
        Objects.requireNonNull(username, "username");
        Objects.requireNonNull(password, "password");
        return ticketEncryptor.encrypt(ticketProvider.getTicket(service, username, password));
    }

    @Override
    public String getToken(String username, String password) throws SSOException {
        Objects.requireNonNull(username, "username");
        Objects.requireNonNull(password, "password");
        return ticketEncryptor.encrypt(ticketProvider.getMultiTicket(username, password));
    }

}
