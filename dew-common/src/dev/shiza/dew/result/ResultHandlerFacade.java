package dev.shiza.dew.result;

import java.util.HashMap;

public interface ResultHandlerFacade {

  static ResultHandlerFacade create() {
    return new ResultHandlerService(new HashMap<>());
  }

  <T> void register(final Class<T> resultType, final ResultHandler<T> resultHandler);

  <T> void process(final T value);
}
