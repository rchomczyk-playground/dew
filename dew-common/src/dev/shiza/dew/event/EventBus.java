package dev.shiza.dew.event;

import dev.shiza.dew.subscription.Subscriber;
import dev.shiza.dew.subscription.SubscribingException;
import dev.shiza.dew.subscription.SubscriptionFacade;

public sealed interface EventBus permits EventBusImpl {

  static EventBus create(
      final EventExecutor eventExecutor, final SubscriptionFacade subscriptionFacade) {
    return new EventBusImpl(eventExecutor, subscriptionFacade);
  }

  static EventBus create(final SubscriptionFacade subscriptionFacade) {
    return create(Runnable::run, subscriptionFacade);
  }

  void subscribe(final Subscriber subscriber) throws SubscribingException;

  void publish(final Event event, final String... targets) throws EventPublishingException;
}
