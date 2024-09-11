package dev.shiza.dew.result;

import java.util.Map;

final class ResultHandlerService implements ResultHandlerFacade {

  private final Map<Class<?>, ResultHandler<?>> handlers;

  ResultHandlerService(final Map<Class<?>, ResultHandler<?>> handlers) {
    this.handlers = handlers;
  }

  @Override
  public <T> void register(final Class<T> resultType, final ResultHandler<T> resultHandler) {
    handlers.put(resultType, resultHandler);
  }

  @Override
  public <T> void process(final T value) {
    if (value == null) {
      return;
    }

    final ResultHandler<?> resultHandler = handlers.get(value.getClass());
    if (resultHandler == null) {
      throw new ResultHandlingException(
          "Could not handle result of type %s, because of missing result handler."
              .formatted(value.getClass().getName()));
    }

    ((ResultHandler<T>) resultHandler).handle(value);
  }
}
