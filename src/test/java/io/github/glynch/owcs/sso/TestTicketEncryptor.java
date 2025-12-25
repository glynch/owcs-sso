package io.github.glynch.owcs.sso;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@TestInstance(Lifecycle.PER_CLASS)
class TestTicketEncryptor {

    TicketEncryptor ticketEncryptor;

    @BeforeAll
    void beforeAll() {
        ticketEncryptor = TicketEncryptor.create();
    }

    @Test
    void testTicketEncryptor() {
        String ticket = "ST-1-qkWa5IgheSZuiI9OG6UN-cas-localhost-1";
        // Not sure how we should test this because the encrypted value is always
        // different.
        String encryptedTicket = ticketEncryptor.encrypt(ticket);
        assertTrue(encryptedTicket.length() == 512);
    }

}
