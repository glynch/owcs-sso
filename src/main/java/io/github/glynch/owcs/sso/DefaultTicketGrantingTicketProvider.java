package io.github.glynch.owcs.sso;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

public class DefaultTicketGrantingTicketProvider extends ProviderSupport implements TicketGrantingTicketProvider {

    private final String casUrl;

    public DefaultTicketGrantingTicketProvider(String casUrl) {
        this.casUrl = casUrl;
    }

    @Override
    public String getTicketGrantingTicket(String username, String password) throws SSOException {
        Assert.hasText(casUrl, "casUrl cannot be empty or null");
        Assert.hasText(username, "username cannot be empty or null");
        Assert.hasText(password, "password cannot be empty or null");

        String url = casUrl + "/v1/tickets";

        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("username", username);
        formData.add("password", password);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(formData, headers);
        String tgt = null;
        try {
            ResponseEntity<String> response = getRestTemplate().postForEntity(url, requestEntity, String.class);
            if (response.getStatusCode() == HttpStatus.CREATED) {
                tgt = response.getHeaders().getLocation().toString();
            }
        } catch (Exception e) {
            throw new SSOException("Error getting ticket granting ticket", e);
        }
        return tgt;
    }

}
