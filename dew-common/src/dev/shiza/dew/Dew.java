package dev.shiza.dew;

import static dev.shiza.dew.initialization.Lazy.lazy;

import dev.shiza.dew.event.EventBus;
import dev.shiza.dew.initialization.Lazy;
import dev.shiza.dew.subscription.SubscriptionFacade;
import org.jetbrains.annotations.ApiStatus.Internal;

public interface Dew {

  /**
   * Provides access to the singleton {@link EventBus} instance.
   * The {@code eventBus()} method returns a lazily initialized, thread-safe singleton instance
   * of {@link EventBus}.
   * This instance is created only once upon the first call to this method and
   * is reused for all later calls.
   *
   * @return the singleton instance of {@link EventBus}.
   */
  static EventBus eventBus() {
    return Instances.EVENT_BUS.get();
  }

  @Internal
  final class Instances {

    private static final Lazy<EventBus> EVENT_BUS =
        lazy(() -> EventBus.create(SubscriptionFacade.create()));

    private Instances() {}
  }
}
