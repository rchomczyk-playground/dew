package dev.shiza.dew.result;

import java.util.Map;
import java.util.Map.Entry;

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

    final ResultHandler<?> resultHandler = getResultHandler(value.getClass());
    if (resultHandler == null) {
      throw new ResultHandlingException(
          "Could not handle result of type %s, because of missing result handler."
              .formatted(value.getClass().getName()));
    }

    ((ResultHandler<T>) resultHandler).handle(value);
  }

  private ResultHandler<?> getResultHandler(final Class<?> clazz) {
    final ResultHandler<?> resultHandler = handlers.get(clazz);
    if (resultHandler != null) {
      return resultHandler;
    }

    for (final Entry<Class<?>, ResultHandler<?>> entry : handlers.entrySet()) {
      final Class<?> resultType = entry.getKey();
      if (resultType.isAssignableFrom(clazz) || clazz.isAssignableFrom(resultType)) {
        return entry.getValue();
      }
    }

    return null;
  }
}
