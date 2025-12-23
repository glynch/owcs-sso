package io.github.glynch.owcs.sso.cache;

import java.util.concurrent.TimeUnit;

import javax.cache.expiry.Duration;
import javax.cache.expiry.ExpiryPolicy;

public class TicketGrantingTicketExpiryPolicy implements ExpiryPolicy {

  private static final Duration DEFAULT_EXPIRY_FOR_CREATION = new Duration(TimeUnit.HOURS, 8);
  private static final Duration DEFAULT_EXPIRY_FOR_ACCESS = new Duration(TimeUnit.HOURS, 2);

  private final Duration expiryForCreation;
  private final Duration expiryForAccess;

  public TicketGrantingTicketExpiryPolicy(Duration expiryForCreation, Duration expiryForAccess) {
    this.expiryForCreation = expiryForCreation;
    this.expiryForAccess = expiryForAccess;
  }

  public TicketGrantingTicketExpiryPolicy() {
    this(DEFAULT_EXPIRY_FOR_CREATION, DEFAULT_EXPIRY_FOR_ACCESS);
  }

  @Override
  public Duration getExpiryForCreation() {
    return expiryForCreation;
  }

  @Override
  public Duration getExpiryForAccess() {
    return expiryForAccess;
  }

  @Override
  public Duration getExpiryForUpdate() {
    return null;
  }

}
