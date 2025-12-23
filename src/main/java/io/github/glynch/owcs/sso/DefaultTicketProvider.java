package io.github.glynch.owcs.sso;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

public class DefaultTicketProvider extends ProviderSupport implements TicketProvider {

    private final TicketGrantingTicketProvider ticketGrantingTicketProvider;

    public DefaultTicketProvider(TicketGrantingTicketProvider ticketGrantingTicketProvider) {
        this.ticketGrantingTicketProvider = ticketGrantingTicketProvider;
    }

    @Override
    public String getTicket(String service, String username, String password) throws SSOException {
        return getServiceTicket(ticketGrantingTicketProvider.getTicketGrantingTicket(username, password), service);
    }

    private String getServiceTicket(String tgt, String service) {
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("service", service);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(formData, headers);
        String serviceTicket = null;
        try {
            ResponseEntity<String> response = getRestTemplate().postForEntity(tgt, requestEntity, String.class);
            if (response.getStatusCode() == HttpStatus.OK) {
                serviceTicket = response.getBody().trim();
            }
        } catch (Exception e) {
            throw new SSOException("Error getting ticket granting ticket", e);
        }
        if (service.equals("*")) {
            serviceTicket = "multi-" + serviceTicket;
        }
        return serviceTicket;
    }

}
