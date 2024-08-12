package dev.shiza.dew;

import static dev.shiza.dew.event.EventBus.getEventBus;
import static dev.shiza.dew.subscription.SubscriptionFacade.getSubscriptionFacade;

import dev.shiza.dew.event.EventBus;

public final class Dew {

  private Dew() {}

  public static EventBus eventBus() {
    return getEventBus(getSubscriptionFacade());
  }
}
