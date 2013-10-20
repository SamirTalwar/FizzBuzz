package com.noodlesandwich.fizzbuzz;

@FunctionalInterface
public interface Lambda {
    Lambda call(Lambda value);
}
