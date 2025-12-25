package io.github.glynch.owcs.sso.cache;

import javax.cache.configuration.CacheEntryListenerConfiguration;
import javax.cache.configuration.Factory;
import javax.cache.event.CacheEntryEventFilter;
import javax.cache.event.CacheEntryListener;

public class TokenCacheEntryListenerConfiguration implements CacheEntryListenerConfiguration<TokenCacheKey, String> {

    @Override
    public Factory<CacheEntryListener<? super TokenCacheKey, ? super String>> getCacheEntryListenerFactory() {
        return LoggingEventListener::new;
    }

    @Override
    public boolean isOldValueRequired() {
        return true;
    }

    @Override
    public Factory<CacheEntryEventFilter<? super TokenCacheKey, ? super String>> getCacheEntryEventFilterFactory() {
        return null;
    }

    @Override
    public boolean isSynchronous() {
        return true;
    }

}
