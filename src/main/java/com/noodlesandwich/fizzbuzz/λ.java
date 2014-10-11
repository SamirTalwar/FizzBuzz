package com.noodlesandwich.fizzbuzz;

@FunctionalInterface
public interface λ {
    λ $(λ value);

    // I'm so sorry. I had to do it.
    static λ λ(λ λ) {
        return λ;
    }
}
