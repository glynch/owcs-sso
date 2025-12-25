package io.github.glynch.owcs.sso;

public interface TicketGrantingTicketProvider {

    String getTicketGrantingTicket(String username, String password) throws SSOException;

    static TicketGrantingTicketProvider create(String casUrl) {
        return new DefaultTicketGrantingTicketProvider(casUrl);
    }

}
