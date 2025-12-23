package io.github.glynch.owcs.sso.cache;

import io.github.glynch.owcs.sso.DefaultTicketGrantingTicketProvider;
import io.github.glynch.owcs.sso.SSOException;
import io.github.glynch.owcs.sso.TicketGrantingTicketProvider;

public class CachingTicketGrantingTicketProvider implements TicketGrantingTicketProvider {

    private final TicketGrantingTicketProvider delegate;

    public CachingTicketGrantingTicketProvider(TicketGrantingTicketProvider delegate) {
        this.delegate = delegate;
    }

    public CachingTicketGrantingTicketProvider(String casUrl) {
        this(new DefaultTicketGrantingTicketProvider(casUrl));
    }

    @Override
    public String getTicketGrantingTicket(String username, String password) throws SSOException {
        return delegate.getTicketGrantingTicket(username, password);
    }

}
