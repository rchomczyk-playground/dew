package dev.shiza.dew.result;

public interface ResultHandler<T> {

  void handle(final T result);
}
