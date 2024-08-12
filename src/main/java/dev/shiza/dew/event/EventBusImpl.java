package dev.shiza.dew.event;

import dev.shiza.dew.subscription.Subscriber;
import dev.shiza.dew.subscription.Subscription;
import dev.shiza.dew.subscription.SubscriptionFacade;
import java.lang.invoke.MethodHandle;
import java.util.Set;

final class EventBusImpl implements EventBus {

  private final SubscriptionFacade subscriptionFacade;

  EventBusImpl(final SubscriptionFacade subscriptionFacade) {
    this.subscriptionFacade = subscriptionFacade;
  }

  @Override
  public void subscribe(final Subscriber subscriber) {
    subscriptionFacade.subscribe(subscriber);
  }

  @Override
  public void publish(final Event event) {
    final Set<Subscription> subscriptions =
        subscriptionFacade.getSubscriptionsByEventType(event.getClass());
    for (final Subscription subscription : subscriptions) {
      notifySubscription(subscription, event);
    }
  }

  private void notifySubscription(final Subscription subscription, final Event event) {
    final Subscriber subscriber = subscription.subscriber();
    for (final MethodHandle invocation : subscription.invocations()) {
      notifySubscribedMethods(invocation, subscriber, event);
    }
  }

  private void notifySubscribedMethods(
      final MethodHandle invocation, final Subscriber subscriber, final Event event) {
    try {
      invocation.invoke(subscriber, event);
    } catch (final Throwable exception) {
      throw new EventPublishingException(
          "Could not publish event, because of unexpected exception during method invocation.",
          exception);
    }
  }
}
