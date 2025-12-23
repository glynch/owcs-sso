package io.github.glynch.owcs.sso.cache;

import java.util.Objects;

import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.configuration.MutableConfiguration;

public class TokenCacheFactory {

    private static final long DEFAULT_TTL = 900;
    private static final String DEFAULT_CACHE_NAME = "tokens";
    private static final CacheManager cacheManager = Caching.getCachingProvider().getCacheManager();
    // private static final

    private TokenCacheFactory() {
    }

    public static Cache<String, String> createTokenCache(String name, long timeToLiveSeconds) {
        Objects.requireNonNull(name, "name must not be null");

        Cache<String, String> cache = cacheManager.getCache(name);
        if (cache == null) {
            MutableConfiguration<String, String> configuration = new MutableConfiguration<>();
            configuration
                    .setExpiryPolicyFactory(TokenExpiryPolicy.factoryOf(timeToLiveSeconds));
            cache = cacheManager.createCache(name, configuration);
        }
        return cache;
    }

    public static Cache<String, String> createTokenCache(long timeToLiveSeconds) {
        return createTokenCache(DEFAULT_CACHE_NAME, timeToLiveSeconds);
    }

    public static Cache<String, String> defaultTokenCache() {
        return createTokenCache(DEFAULT_CACHE_NAME, DEFAULT_TTL);
    }

}
