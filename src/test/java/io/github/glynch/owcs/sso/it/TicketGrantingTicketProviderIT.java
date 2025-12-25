package io.github.glynch.owcs.sso.it;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import io.github.glynch.owcs.sso.TicketGrantingTicketProvider;
import io.github.glynch.owcs.sso.cache.CachingTicketGrantingTicketProvider;
import io.github.glynch.owcs.test.containers.JSKContainer;

@TestInstance(Lifecycle.PER_CLASS)
class TicketGrantingTicketProviderIT {

    private static final String username = "fwadmin";
    private static final String password = "xceladmin";
    private JSKContainer jskContainer;
    private TicketGrantingTicketProvider ticketGrantingTicketProvider;

    @BeforeAll
    void beforeAll() {
        jskContainer = new JSKContainer("grahamlynch/jsk:12.2.1.3.0-samples");
        jskContainer.start();
        ticketGrantingTicketProvider = CachingTicketGrantingTicketProvider.create(jskContainer.getCasUrl());
    }

    @Test
    void testTicketGrantingTicket() {
        String tgt = ticketGrantingTicketProvider.getTicketGrantingTicket(username, password);
        assertTrue(tgt.startsWith(jskContainer.getCasUrl() + "/v1/tickets/TGT-"));
    }

    @Test
    void testCachingTicketGrantingTicketProvider() {
        String tgt1 = ticketGrantingTicketProvider.getTicketGrantingTicket(username, password);
        String tgt2 = ticketGrantingTicketProvider.getTicketGrantingTicket(username, password);
        assertTrue(tgt1.equals(tgt2));
    }

    @AfterAll
    void afterAll() {
        jskContainer.stop();
    }

}
