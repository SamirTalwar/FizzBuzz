package com.noodlesandwich.fizzbuzz;

import java.util.Iterator;

import static com.noodlesandwich.fizzbuzz.Lambdas.*;

public final class FizzBuzz {
    public static Iterable<String> upTo(int max) {
        return new Range(1, max).map(Lambdas::fromInt).map((i) -> {
            if (toInt(i) % 15 == 0) {
                return "FizzBuzz";
            }
            if (toInt(i) % 3 == 0) {
                return "Fizz";
            }
            if (toInt(i) % 5 == 0) {
                return "Buzz";
            }
            return Integer.toString(toInt(i));
        });
    }

    public static final class Range implements Iterable<Integer> {
        private final int min;
        private final int max;

        public Range(int min, int max) {
            this.min = min;
            this.max = max;
        }

        @Override
        public Iterator<Integer> iterator() {
            return new Iterator<Integer>() {
                int currentValue = min;

                @Override
                public Integer next() {
                    return currentValue++;
                }

                @Override
                public boolean hasNext() {
                    return currentValue <= max;
                }
            };
        }
    }
}
