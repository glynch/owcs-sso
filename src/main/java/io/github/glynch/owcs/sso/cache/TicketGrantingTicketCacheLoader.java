package io.github.glynch.owcs.sso.cache;

import java.util.HashMap;
import java.util.Map;

import javax.cache.integration.CacheLoader;
import javax.cache.integration.CacheLoaderException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.github.glynch.owcs.sso.TicketGrantingTicketProvider;

public class TicketGrantingTicketCacheLoader implements CacheLoader<TicketGrantingTicketCacheKey, String> {

    private static final Logger LOGGER = LoggerFactory.getLogger(TicketGrantingTicketCacheLoader.class);

    private final TicketGrantingTicketProvider ticketGrantingTicketProvider;

    public TicketGrantingTicketCacheLoader(TicketGrantingTicketProvider ticketGrantingTicketProvider) {
        this.ticketGrantingTicketProvider = ticketGrantingTicketProvider;
    }

    @Override
    public String load(TicketGrantingTicketCacheKey key) throws CacheLoaderException {
        LOGGER.debug("Loading tgt for {}", key);
        return ticketGrantingTicketProvider.getTicketGrantingTicket(key.getUsername(), key.getPassword());
    }

    @Override
    public Map<TicketGrantingTicketCacheKey, String> loadAll(Iterable<? extends TicketGrantingTicketCacheKey> keys)
            throws CacheLoaderException {
        Map<TicketGrantingTicketCacheKey, String> data = new HashMap<>();
        for (TicketGrantingTicketCacheKey key : keys) {
            data.put(key, load(key));
        }
        return data;
    }

}
