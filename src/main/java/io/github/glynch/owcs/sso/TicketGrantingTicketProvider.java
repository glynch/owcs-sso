package io.github.glynch.owcs.sso;

public interface TicketGrantingTicketProvider {

    String getTicketGrantingTicket(String username, String password) throws SSOException;

}
