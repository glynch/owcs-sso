package io.github.glynch.owcs.sso;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

public class DefaultTokenProvider implements TokenProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultTokenProvider.class);
    private final TicketProvider ticketProvider;
    private final TicketEncryptor ticketEncryptor;

    DefaultTokenProvider(TicketProvider ticketProvider, TicketEncryptor ticketEncryptor) {
        this.ticketProvider = ticketProvider;
        this.ticketEncryptor = ticketEncryptor;
    }

    @Override
    public String getToken(String service, String username, String password) throws TokenProviderException {
        Assert.hasText(service, "service cannot be empty or null");
        Assert.hasText(username, "username cannot be empty or null");
        Assert.hasText(password, "password cannot be empty or null");
        String token = null;
        try {
            token = ticketEncryptor.encrypt(ticketProvider.getTicket(service, username, password));
        } catch (SSOException e) {
            throw new TokenProviderException(service, username, password, e);
        }
        return token;
    }

    @Override
    public String getToken(String username, String password) throws SSOException {
        return getToken("*", username, password);
    }

}
