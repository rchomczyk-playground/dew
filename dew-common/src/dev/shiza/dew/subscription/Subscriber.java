package dev.shiza.dew.subscription;

public interface Subscriber {

  default String identity() {
    return null;
  }
}
