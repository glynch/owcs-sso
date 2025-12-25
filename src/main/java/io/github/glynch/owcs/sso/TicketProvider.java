package io.github.glynch.owcs.sso;

/*
 * This interface provides a contract for classes that provide tickets for CAS Single Sign-On (SSO) authentication.
 */
public interface TicketProvider {

    /*
     * This method returns a ticket for the given service.
     * 
     * Can be used only during some limited period of time for one resource and only
     * once
     * 
     * @param service The service for which the ticket is requested.
     * 
     * @param username The username for which the ticket is requested
     * 
     * @param password The password for which the ticket is requested.
     * 
     * @return The ticket for the given service.
     * 
     * @throws SSOException If an error occurs while retrieving the ticket.
     */
    String getTicket(String service, String username, String password) throws TicketProviderException;

    /*
     * This method returns a multi-ticket for the given username/password.
     * 
     * Can be used only during some limited period, multiple times for any resource.
     * 
     * @param username The username for which the ticket is requested
     * 
     * @param password The password for which the ticket is requested.
     *
     * @return The multi-ticket for the given username.
     * 
     * @throws SSOException If an error occurs while retrieving the multi-ticket.
     */
    default String getMultiTicket(String username, String password) throws TicketProviderException {
        return getTicket("*", username, password);
    }

    static TicketProvider create(TicketGrantingTicketProvider ticketGrantingTicketProvider) {
        return new DefaultTicketProvider(ticketGrantingTicketProvider);
    }

    static TicketProvider create(String casUrl) {
        return create(TicketGrantingTicketProvider.create(casUrl));
    }

}
