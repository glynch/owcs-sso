package io.github.glynch.owcs.sso.cache;

import javax.cache.event.CacheEntryCreatedListener;
import javax.cache.event.CacheEntryEvent;
import javax.cache.event.CacheEntryExpiredListener;
import javax.cache.event.CacheEntryListenerException;
import javax.cache.event.CacheEntryRemovedListener;
import javax.cache.event.CacheEntryUpdatedListener;

public abstract class AbstractCacheEventListener<K, V> implements
        CacheEntryCreatedListener<K, V>, CacheEntryUpdatedListener<K, V>,
        CacheEntryRemovedListener<K, V>, CacheEntryExpiredListener<K, V> {

    @Override
    public void onCreated(Iterable<CacheEntryEvent<? extends K, ? extends V>> events)
            throws CacheEntryListenerException {
        for (CacheEntryEvent<? extends K, ? extends V> event : events) {
            onCreated(event);
        }
    }

    @Override
    public void onUpdated(Iterable<CacheEntryEvent<? extends K, ? extends V>> events)
            throws CacheEntryListenerException {
        for (CacheEntryEvent<? extends K, ? extends V> event : events) {
            onUpdated(event);
        }
    }

    @Override
    public void onRemoved(Iterable<CacheEntryEvent<? extends K, ? extends V>> events)
            throws CacheEntryListenerException {
        for (CacheEntryEvent<? extends K, ? extends V> event : events) {
            onRemoved(event);
        }
    }

    @Override
    public void onExpired(Iterable<CacheEntryEvent<? extends K, ? extends V>> events)
            throws CacheEntryListenerException {
        for (CacheEntryEvent<? extends K, ? extends V> event : events) {
            onExpired(event);
        }
    }

    protected void onCreated(CacheEntryEvent<? extends K, ? extends V> event) {
    }

    protected void onUpdated(CacheEntryEvent<? extends K, ? extends V> event) {
    }

    protected void onRemoved(CacheEntryEvent<? extends K, ? extends V> event) {
    }

    protected void onExpired(CacheEntryEvent<? extends K, ? extends V> event) {
    }

}
