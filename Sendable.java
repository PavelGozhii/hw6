package com.company;

public interface Sendable<T> {
    String getKeyForMailService();

    T getValueForMailService();
}
