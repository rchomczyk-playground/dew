package dev.shiza.dew.event;

import dev.shiza.dew.result.ResultHandler;
import dev.shiza.dew.result.ResultHandlerFacade;
import dev.shiza.dew.subscription.Subscriber;
import dev.shiza.dew.subscription.SubscribingException;
import dev.shiza.dew.subscription.SubscriptionFacade;
import org.jetbrains.annotations.Contract;

public sealed interface EventBus permits EventBusImpl {

  static EventBus create(
      final SubscriptionFacade subscriptionFacade, final ResultHandlerFacade resultHandlerFacade) {
    return new EventBusImpl(subscriptionFacade, resultHandlerFacade);
  }

  static EventBus create() {
    return create(SubscriptionFacade.create(), ResultHandlerFacade.create());
  }

  @Contract("_ -> this")
  EventBus publisher(final EventPublisher eventPublisher);

  @Contract("_, _ -> this")
  <T> EventBus result(final Class<T> resultType, final ResultHandler<T> resultHandler);

  void subscribe(final Subscriber subscriber) throws SubscribingException;

  void publish(final EventPublisher eventPublisher, final Event event, final String... targets)
      throws EventPublishingException;

  void publish(final Event event, final String... targets) throws EventPublishingException;
}
