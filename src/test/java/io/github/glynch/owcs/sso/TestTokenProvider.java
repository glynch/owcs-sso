package io.github.glynch.owcs.sso;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import io.github.glynch.owcs.sso.cache.CachingTokenProvider;
import io.github.glynch.owcs.sso.cache.TokenCacheFactory;
import io.github.glynch.owcs.test.containers.JSKContainer;

@TestInstance(Lifecycle.PER_CLASS)
public class TestTokenProvider {

    private JSKContainer jskContainer;
    private TokenProvider tokenProvider;

    @BeforeAll
    void beforeAll() {
        jskContainer = new JSKContainer("grahamlynch/jsk:12.2.1.3.0-samples");
        jskContainer.start();
        tokenProvider = TokenProvider.create();
    }

    @Test
    void testToken() {
        String token = tokenProvider.getToken(jskContainer.getBaseUrl(), "fwadmin", "xceladmin");
        assertNotNull(token);
    }

    @Test
    void testCachedToken() {
        CachingTokenProvider cachingTokenProvider = CachingTokenProvider.create(TokenCacheFactory.defaultTokenCache());
        String token = cachingTokenProvider.getToken(jskContainer.getBaseUrl(), "fwadmin", "xceladmin");
        String cachedToken = cachingTokenProvider.getToken(jskContainer.getBaseUrl(), "fwadmin", "xceladmin");
        assertEquals(token, cachedToken);
    }

    @AfterAll
    void afterAll() {
        jskContainer.stop();
    }

}
