package io.github.glynch.owcs;

import io.github.glynch.owcs.sso.cache.CachingTokenProvider;
import io.github.glynch.owcs.sso.cache.TokenCacheFactory;

public class Main {
    public static void main(String[] args) {

        CachingTokenProvider tokenProvider = CachingTokenProvider.create(TokenCacheFactory.defaultTokenCache());
        String token = tokenProvider.getToken("http://localhost:7003/sites", "fwadmin", "xceladmin");
        System.out.println("Token: " + token);

    }
}