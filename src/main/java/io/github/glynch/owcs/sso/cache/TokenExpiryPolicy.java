package io.github.glynch.owcs.sso.cache;

import java.util.concurrent.TimeUnit;

import javax.cache.configuration.Factory;
import javax.cache.configuration.FactoryBuilder;
import javax.cache.expiry.Duration;
import javax.cache.expiry.ExpiryPolicy;

public class TokenExpiryPolicy implements ExpiryPolicy {

    private static final Duration DEFAULT_DURATION = new Duration(TimeUnit.SECONDS, 900);
    private final Duration expiryDuration;

    public TokenExpiryPolicy(Duration expiryDuration) {
        this.expiryDuration = expiryDuration;
    }

    public TokenExpiryPolicy(long timeToLiveSeconds) {
        this(new Duration(TimeUnit.SECONDS, timeToLiveSeconds));
    }

    public TokenExpiryPolicy() {
        this(DEFAULT_DURATION);
    }

    /**
     * Obtains a {@link Factory} for an Token {@link ExpiryPolicy}.
     *
     * @return a {@link Factory} for an Token {@link ExpiryPolicy}.
     */
    public static Factory<TokenExpiryPolicy> factoryOf(Duration duration) {
        return new FactoryBuilder.SingletonFactory<>(new TokenExpiryPolicy(duration));
    }

    public static Factory<TokenExpiryPolicy> factoryOf(long timeToLiveSeconds) {
        return new FactoryBuilder.SingletonFactory<>(new TokenExpiryPolicy(timeToLiveSeconds));
    }

    @Override
    public Duration getExpiryForCreation() {
        return expiryDuration;
    }

    @Override
    public Duration getExpiryForAccess() {
        return expiryDuration;
    }

    @Override
    public Duration getExpiryForUpdate() {
        return null;
    }

}
