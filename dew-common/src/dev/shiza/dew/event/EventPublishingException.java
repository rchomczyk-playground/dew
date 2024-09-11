package dev.shiza.dew.event;

public final class EventPublishingException extends RuntimeException {

  public EventPublishingException(final String message, final Throwable cause) {
    super(message, cause);
  }

  public EventPublishingException(final String message) {
    super(message);
  }
}
