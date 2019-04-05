package com.company;

import java.util.Objects;

public class Pair<F, S> {
    private F firstElement;
    private S secondElement;


    private Pair(F firstElement, S secondElement) {
        this.firstElement = firstElement;
        this.secondElement = secondElement;
    }

    public static Pair of(Object firstElement, Object secondElement) {
        return new Pair(firstElement, secondElement);
    }

    public F getFirst() {

        return firstElement;
    }

    public S getSecond() {
        return secondElement;
    }

    @Override
    public int hashCode() {
        if (firstElement != null && secondElement != null) {
            return Objects.hashCode(firstElement) ^ Objects.hashCode(secondElement);
        } else if (firstElement == null) {
            return Objects.hashCode(secondElement);
        } else {
            return Objects.hashCode(firstElement);
        }
    }

    public boolean equals(Pair a) {
        if (this == null && a == null) {
            return true;
        } else if (this == null || a == null) {
            return false;
        }
        if (this.firstElement.equals(a.firstElement) &&
                this.secondElement.equals(a.secondElement) &&
                this.hashCode() == a.hashCode()) {
            return true;
        }
        return false;
    }
}
