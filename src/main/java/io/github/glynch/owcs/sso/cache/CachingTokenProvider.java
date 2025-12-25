package io.github.glynch.owcs.sso.cache;

import java.util.UUID;

import javax.cache.Cache;
import javax.cache.configuration.Configuration;
import javax.cache.configuration.MutableConfiguration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.github.glynch.owcs.sso.SSOException;
import io.github.glynch.owcs.sso.TicketGrantingTicketProvider;
import io.github.glynch.owcs.sso.TicketProvider;
import io.github.glynch.owcs.sso.TokenProvider;

public class CachingTokenProvider implements TokenProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(CachingTokenProvider.class);;
    private static final String CACHE_NAME_PREFIX = "tokens-";
    private final TokenProvider delegate;
    private final Cache<TokenCacheKey, String> cache;

    public CachingTokenProvider(TokenProvider delegate) {
        this.delegate = delegate;
        this.cache = CacheFactory.getOrCreateCache(CACHE_NAME_PREFIX + UUID.randomUUID().toString(),
                getConfiguration(delegate, TokenExpiryPolicy.DEFAULT_TIME_TO_LIVE_SECONDS));
    }

    @Override
    public String getToken(String service, String username, String password) throws SSOException {
        TokenCacheKey cacheKey = new TokenCacheKey(username, password);
        String token = null;
        // Only cache multi tickets because service tickets are single use.
        if (service.equals("*")) {
            // Token Cache is read through, so if the token does not exists it will be
            // loaded.
            token = cache.get(cacheKey);
        } else {
            token = delegate.getToken(service, username, password);
        }

        return token;
    }

    private Configuration<TokenCacheKey, String> getConfiguration(TokenProvider delegate, long timeToLiveSeconds) {
        MutableConfiguration<TokenCacheKey, String> configuration = new MutableConfiguration<>();
        configuration
                .setExpiryPolicyFactory(() -> new TokenExpiryPolicy(timeToLiveSeconds))
                .setCacheLoaderFactory(() -> new TokenCacheLoader(delegate))
                .setReadThrough(true)
                .addCacheEntryListenerConfiguration(new TokenCacheEntryListenerConfiguration());
        return configuration;
    }

    @Override
    public String getToken(String username, String password) throws SSOException {
        return getToken("*", username, password);
    }

    public Cache<TokenCacheKey, String> getCache() {
        return cache;
    }

    public static CachingTokenProvider create(TokenProvider tokenProvider) {
        return new CachingTokenProvider(tokenProvider);
    }

    public static CachingTokenProvider create(String casUrl) {
        TicketGrantingTicketProvider ticketGrantingTicketProvider = CachingTicketGrantingTicketProvider.create(casUrl);
        TicketProvider ticketProvider = TicketProvider.create(ticketGrantingTicketProvider);
        TokenProvider tokenProvider = TokenProvider.create(ticketProvider);
        return new CachingTokenProvider(tokenProvider);
    }

}
