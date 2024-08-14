package dev.shiza.dew;

import dev.shiza.dew.event.EventBus;
import dev.shiza.dew.subscription.SubscriptionFacade;

public final class Dew {

  private Dew() {}

  public static EventBus create() {
    return EventBus.create(SubscriptionFacade.create());
  }
}
