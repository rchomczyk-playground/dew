package dev.shiza.dew.event;

@FunctionalInterface
public interface EventPublisher {

  void execute(final Runnable task);
}
