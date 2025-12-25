package io.github.glynch.owcs.sso.cache;

import java.util.concurrent.TimeUnit;

import javax.cache.expiry.Duration;
import javax.cache.expiry.ExpiryPolicy;

public class TokenExpiryPolicy implements ExpiryPolicy {

    // This is the same as the default cs.timeout in WebCenter Sites
    public static final long DEFAULT_TIME_TO_LIVE_SECONDS = 900;
    private final Duration expiryForCreation;
    private final Duration expiryForAccess;

    public TokenExpiryPolicy(long timeToLiveSeconds) {
        this.expiryForCreation = new Duration(TimeUnit.SECONDS, timeToLiveSeconds);
        this.expiryForAccess = new Duration(TimeUnit.SECONDS, timeToLiveSeconds);
    }

    public TokenExpiryPolicy() {
        this(DEFAULT_TIME_TO_LIVE_SECONDS);
    }

    @Override
    public Duration getExpiryForCreation() {
        return expiryForCreation;
    }

    @Override
    public Duration getExpiryForAccess() {
        return expiryForAccess;
    }

    @Override
    public Duration getExpiryForUpdate() {
        return null;
    }

}
