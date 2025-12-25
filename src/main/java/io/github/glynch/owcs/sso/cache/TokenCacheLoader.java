package io.github.glynch.owcs.sso.cache;

import java.util.HashMap;
import java.util.Map;

import javax.cache.integration.CacheLoader;
import javax.cache.integration.CacheLoaderException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.github.glynch.owcs.sso.TokenProvider;

public class TokenCacheLoader implements CacheLoader<TokenCacheKey, String> {
    private static final Logger LOGGER = LoggerFactory.getLogger(TokenCacheLoader.class);

    private final TokenProvider tokenProvider;

    public TokenCacheLoader(TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @Override
    public String load(TokenCacheKey key) throws CacheLoaderException {
        String username = key.getUsername();
        String password = key.getPassword();
        LOGGER.debug("Loading token for {}", key);
        return tokenProvider.getToken(username, password);
    }

    @Override
    public Map<TokenCacheKey, String> loadAll(Iterable<? extends TokenCacheKey> keys) throws CacheLoaderException {
        Map<TokenCacheKey, String> data = new HashMap<>();
        for (TokenCacheKey key : keys) {
            data.put(key, load(key));
        }
        return data;
    }

}
