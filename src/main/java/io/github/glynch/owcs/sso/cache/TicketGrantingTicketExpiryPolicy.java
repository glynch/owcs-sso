package io.github.glynch.owcs.sso.cache;

import java.util.concurrent.TimeUnit;

import javax.cache.expiry.Duration;
import javax.cache.expiry.ExpiryPolicy;

public class TicketGrantingTicketExpiryPolicy implements ExpiryPolicy {

  public static final long DEFAULT_TIME_TO_LIVE_SECONDS = 28800; // 8 hours
  public static final long DEFAULT_TIME_TO_IDLE_SECONDS = 7200; // 2 hours

  private final Duration expiryForCreation;
  private final Duration expiryForAccess;

  public TicketGrantingTicketExpiryPolicy(long timeToLiveSeconds, long timeToIdleSeconds) {
    this.expiryForCreation = new Duration(TimeUnit.SECONDS, timeToLiveSeconds);
    this.expiryForAccess = new Duration(TimeUnit.SECONDS, timeToIdleSeconds);
  }

  public TicketGrantingTicketExpiryPolicy() {
    this(DEFAULT_TIME_TO_LIVE_SECONDS, DEFAULT_TIME_TO_IDLE_SECONDS);
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
