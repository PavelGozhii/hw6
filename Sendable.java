package com.company;

public interface Sendable<T> {
    String getKey();

    T getValue();
}
