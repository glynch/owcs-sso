package io.github.glynch.owcs.sso;

import org.springframework.util.Assert;

import com.fatwire.security.util.CSRFUtility;

public class DefaultTicketEncryptor implements TicketEncryptor {

    @Override
    public String encrypt(String ticket) throws TicketEncryptionException {
        Assert.hasText(ticket, "ticket cannot be empty or null");
        String encryptedTicket = null;
        try {
            encryptedTicket = CSRFUtility.encrypt(ticket);
        } catch (Exception e) {
            throw new TicketEncryptionException(ticket, e);
        }
        return encryptedTicket;
    }

}
