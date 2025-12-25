package io.github.glynch.owcs.sso.cache;

import java.util.UUID;

import javax.cache.Cache;
import javax.cache.configuration.Configuration;
import javax.cache.configuration.MutableConfiguration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import io.github.glynch.owcs.sso.SSOException;
import io.github.glynch.owcs.sso.TicketGrantingTicketProvider;

public class CachingTicketGrantingTicketProvider implements TicketGrantingTicketProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(CachingTicketGrantingTicketProvider.class);
    private static final String CACHE_NAME_PREFIX = "tgt-";
    private final Cache<TicketGrantingTicketCacheKey, String> cache;

    public CachingTicketGrantingTicketProvider(TicketGrantingTicketProvider delegate, long timeToLiveSeconds,
            long timeToIdleSeconds) {
        this.cache = CacheFactory.getOrCreateCache(CACHE_NAME_PREFIX + UUID.randomUUID().toString(),
                getConfiguration(delegate, timeToLiveSeconds, timeToIdleSeconds));
    }

    public CachingTicketGrantingTicketProvider(TicketGrantingTicketProvider ticketGrantingTicketProvider) {
        this(ticketGrantingTicketProvider, TicketGrantingTicketExpiryPolicy.DEFAULT_TIME_TO_LIVE_SECONDS,
                TicketGrantingTicketExpiryPolicy.DEFAULT_TIME_TO_IDLE_SECONDS);
    }

    public CachingTicketGrantingTicketProvider(String casUrl, long timeToLiveSeconds, long timeToIdleSeconds) {
        this(TicketGrantingTicketProvider.create(casUrl), timeToLiveSeconds,
                timeToIdleSeconds);
    }

    public CachingTicketGrantingTicketProvider(String casUrl) {
        this(casUrl, TicketGrantingTicketExpiryPolicy.DEFAULT_TIME_TO_LIVE_SECONDS,
                TicketGrantingTicketExpiryPolicy.DEFAULT_TIME_TO_IDLE_SECONDS);
    }

    public static CachingTicketGrantingTicketProvider create(TicketGrantingTicketProvider ticketGrantingTicketProvider,
            long timeToLiveSeconds, long timeToIdleSeconds) {
        Assert.isTrue(timeToLiveSeconds > timeToIdleSeconds, "timeToLiveSeconds must be > timeToIdleSeconds");
        return new CachingTicketGrantingTicketProvider(ticketGrantingTicketProvider, timeToLiveSeconds,
                timeToIdleSeconds);
    }

    public static CachingTicketGrantingTicketProvider create(
            TicketGrantingTicketProvider ticketGrantingTicketProvider) {
        return new CachingTicketGrantingTicketProvider(ticketGrantingTicketProvider);
    }

    public static CachingTicketGrantingTicketProvider create(String casUrl, long timeToLiveSeconds,
            long timeToIdleSeconds) {
        return new CachingTicketGrantingTicketProvider(casUrl, timeToLiveSeconds, timeToIdleSeconds);
    }

    public static CachingTicketGrantingTicketProvider create(String casUrl) {
        return new CachingTicketGrantingTicketProvider(casUrl);
    }

    private Configuration<TicketGrantingTicketCacheKey, String> getConfiguration(TicketGrantingTicketProvider delegate,
            long timeToLiveSeconds, long timeToIdleSeconds) {
        MutableConfiguration<TicketGrantingTicketCacheKey, String> configuration = new MutableConfiguration<>();
        configuration
                .setExpiryPolicyFactory(
                        () -> new TicketGrantingTicketExpiryPolicy(timeToLiveSeconds, timeToIdleSeconds))
                .setCacheLoaderFactory(() -> new TicketGrantingTicketCacheLoader(delegate))
                .setReadThrough(true)
                .addCacheEntryListenerConfiguration(new TicketGrantingTicketCacheListenerConfiguration());
        return configuration;
    }

    public Cache<TicketGrantingTicketCacheKey, String> getCache() {
        return cache;
    }

    @Override
    public String getTicketGrantingTicket(String username, String password) throws SSOException {
        TicketGrantingTicketCacheKey key = TicketGrantingTicketCacheKey.of(username, password);
        return cache.get(key);
    }

}
