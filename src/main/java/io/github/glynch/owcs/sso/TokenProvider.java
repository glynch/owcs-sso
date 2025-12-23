package io.github.glynch.owcs.sso;

import javax.cache.Cache;

import io.github.glynch.owcs.sso.cache.CachingTokenProvider;

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
    String getToken(String service, String username, String password) throws SSOException;

    String getToken(String username, String password) throws SSOException;

    static TokenProvider create(String casUrl) {
        return new DefaultTokenProvider(new DefaultTicketProvider(new DefaultTicketGrantingTicketProvider(casUrl)),
                new DefaultTicketEncryptor());
    }

    static CachingTokenProvider create(String casUrl, Cache<String, String> cache) {

        TokenProvider delegate = new DefaultTokenProvider(
                new DefaultTicketProvider(new DefaultTicketGrantingTicketProvider(casUrl)),
                new DefaultTicketEncryptor());

        return new CachingTokenProvider(cache, delegate);
    }

}
