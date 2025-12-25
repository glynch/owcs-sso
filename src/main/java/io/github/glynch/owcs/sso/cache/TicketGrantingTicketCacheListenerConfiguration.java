package io.github.glynch.owcs.sso.cache;

import javax.cache.configuration.CacheEntryListenerConfiguration;
import javax.cache.configuration.Factory;
import javax.cache.event.CacheEntryEventFilter;
import javax.cache.event.CacheEntryListener;

public class TicketGrantingTicketCacheListenerConfiguration
        implements CacheEntryListenerConfiguration<TicketGrantingTicketCacheKey, String> {

    @Override
    public Factory<CacheEntryListener<? super TicketGrantingTicketCacheKey, ? super String>> getCacheEntryListenerFactory() {
        return LoggingEventListener::new;
    }

    @Override
    public boolean isOldValueRequired() {
        return true;
    }

    @Override
    public Factory<CacheEntryEventFilter<? super TicketGrantingTicketCacheKey, ? super String>> getCacheEntryEventFilterFactory() {
        return null;
    }

    @Override
    public boolean isSynchronous() {
        return true;
    }

}
