package io.github.glynch.owcs.sso.it;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import io.github.glynch.owcs.sso.TicketProvider;
import io.github.glynch.owcs.test.containers.JSKContainer;

@TestInstance(Lifecycle.PER_CLASS)
class TicketProviderIT {

    private static final String username = "fwadmin";
    private static final String password = "xceladmin";
    private JSKContainer jskContainer;
    private TicketProvider ticketProvider;

    @BeforeEach
    void beforeAll() {
        jskContainer = new JSKContainer("grahamlynch/jsk:12.2.1.3.0-samples");
        jskContainer.start();
        ticketProvider = TicketProvider.create(jskContainer.getCasUrl());
    }

    @Test
    void testTicketProviderServiceTicket() {
        String ticket = ticketProvider.getTicket("http://localhost:7003/sites/REST", username, password);
        assertTrue(ticket.startsWith("ST-"));
    }

    @Test
    void testTicketProviderMultiTicket() {
        String multiTicket = ticketProvider.getMultiTicket(username, password);
        assertTrue(multiTicket.startsWith("multi-"));
    }

    @AfterEach
    void afterAll() {
        jskContainer.stop();
    }

}
