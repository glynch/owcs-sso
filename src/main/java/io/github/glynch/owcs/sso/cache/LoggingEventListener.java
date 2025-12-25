package io.github.glynch.owcs.sso.cache;

import javax.cache.event.CacheEntryEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingEventListener<K, V> extends AbstractCacheEventListener<K, V> {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingEventListener.class);

    @Override
    protected void onCreated(CacheEntryEvent<? extends K, ? extends V> event) {
        LOGGER.trace("Created {}: {}", event.getKey(), event.getValue());
    }

    @Override
    protected void onExpired(CacheEntryEvent<? extends K, ? extends V> event) {
        LOGGER.trace("Expired {}: {}", event.getKey(), event.getValue());

    }

    @Override
    protected void onRemoved(CacheEntryEvent<? extends K, ? extends V> event) {
        LOGGER.trace("Removed {}: {}", event.getKey(), event.getValue());

    }

    @Override
    protected void onUpdated(CacheEntryEvent<? extends K, ? extends V> event) {
        LOGGER.trace("Updated {}: {} ({})", event.getKey(), event.getValue(),
                event.getOldValue());

    }

}
