package com.noodlesandwich.fizzbuzz;

public final class FizzBuzz {
    public static String forValue(int i) {
        if (i % 15 == 0) {
            return "FizzBuzz";
        }
        if (i % 3 == 0) {
            return "Fizz";
        }
        if (i % 5 == 0) {
            return "Buzz";
        }
        return Integer.toString(i);
    }
}
