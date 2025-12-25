package io.github.glynch.owcs.sso;

/**
 * 
 * 
 * @see TicketProvider
 * @see TicketEncryptor
 */
public interface TokenProvider {

    String X_CSRF_TOKEN = "X-CSRF-TOKEN";

    /**
     * 
     * @param service  the service to generate the toke for
     * @param username the username
     * @param password the password
     * @return the encrypted token to use when authenticating {@value X_CSRF_TOKEN}
     * @throws SSOException
     */
    String getToken(String service, String username, String password) throws TokenProviderException;

    String getToken(String username, String password) throws TokenProviderException;

    static TokenProvider create(TicketProvider ticketProvider, TicketEncryptor ticketEncryptor) {
        return new DefaultTokenProvider(ticketProvider, ticketEncryptor);
    }

    static TokenProvider create(TicketProvider ticketProvider) {
        return create(ticketProvider,
                TicketEncryptor.create());
    }

    static TokenProvider create(String casUrl) {
        return create(TicketProvider.create(casUrl));
    }

}
