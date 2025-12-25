package io.github.glynch.owcs.sso.it;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import io.github.glynch.owcs.sso.TokenProvider;
import io.github.glynch.owcs.sso.cache.CachingTokenProvider;
import io.github.glynch.owcs.test.containers.JSKContainer;

@TestInstance(Lifecycle.PER_CLASS)
class TokenProviderIT {

    private static final String username = "fwadmin";
    private static final String password = "xceladmin";
    private JSKContainer jskContainer;
    private TokenProvider tokenProvider;
    private TokenProvider cachingTokenProvider;

    @BeforeAll
    void beforeAll() {
        jskContainer = new JSKContainer("grahamlynch/jsk:12.2.1.3.0-samples");
        jskContainer.start();
        tokenProvider = TokenProvider.create(jskContainer.getCasUrl());
        cachingTokenProvider = CachingTokenProvider.create(jskContainer.getCasUrl());
    }

    @Test
    void testToken() {
        String token = tokenProvider.getToken("*", username, password);
        assertTrue(token.length() == 512);
    }

    @Test
    void testCachingTokenPriver() {
        String token1 = cachingTokenProvider.getToken(username, password);
        String token2 = cachingTokenProvider.getToken(username, password);
        assertTrue(token1.equals(token2));

    }

    @AfterAll
    void afterAll() {
        jskContainer.stop();
    }

}
