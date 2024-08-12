package dev.shiza.dew.subscription;

public final class SubscribingException extends RuntimeException {

  public SubscribingException(final String message) {
    super(message);
  }

  public SubscribingException(final String message, final Throwable cause) {
    super(message, cause);
  }
}
