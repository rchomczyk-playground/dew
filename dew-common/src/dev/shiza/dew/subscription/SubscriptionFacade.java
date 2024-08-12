package dev.shiza.dew.subscription;

import dev.shiza.dew.event.Event;
import java.util.HashMap;
import java.util.Set;

public sealed interface SubscriptionFacade permits SubscriptionService {

  static SubscriptionFacade getSubscriptionFacade() {
    return new SubscriptionService(new HashMap<>());
  }

  void subscribe(final Subscriber subscriber) throws SubscribingException;

  Set<Subscription> getSubscriptionsByEventType(final Class<? extends Event> eventType);
}
