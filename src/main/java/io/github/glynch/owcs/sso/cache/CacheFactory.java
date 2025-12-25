package io.github.glynch.owcs.sso.cache;

import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.configuration.Configuration;

public class CacheFactory {

    private static final CacheManager cacheManager = Caching.getCachingProvider().getCacheManager();

    private CacheFactory() {

    }

    public static <K, V> Cache<K, V> getOrCreateCache(String name, Configuration<K, V> configuration) {
        Cache<K, V> cache = cacheManager.getCache(name);
        if (cache == null) {
            cache = cacheManager.createCache(name, configuration);
        }
        return cache;
    }
}
