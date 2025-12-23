package io.github.glynch.owcs.sso;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import okhttp3.OkHttpClient;

public abstract class ProviderSupport {

    protected static final Logger LOGGER = LoggerFactory.getLogger("io.github.glynch.owcs.sso");

    private static final OkHttpClient okHttpClient = new OkHttpClient();
    private static final ClientHttpRequestInterceptor loggingInterceptor = new LoggingClientHttpRequestInterceptor();;
    private static final OkHttp3ClientHttpRequestFactory requestFactory = new OkHttp3ClientHttpRequestFactory(
            okHttpClient);
    private static final RestTemplate restTemplate = new RestTemplate(requestFactory);

    static {
        restTemplate.setInterceptors(Arrays.asList(loggingInterceptor));
        if (LOGGER.isTraceEnabled()) {
            restTemplate.setRequestFactory(new BufferingClientHttpRequestFactory(requestFactory));
        } else {
            restTemplate.setRequestFactory(requestFactory);
        }
    }

    protected RestTemplate getRestTemplate() {
        return restTemplate;
    }

}
