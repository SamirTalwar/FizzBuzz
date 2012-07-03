package com.noodlesandwich.fizzbuzz;

import java.util.Iterator;

public final class FizzBuzz {
    public static Iterable<String> upTo(int max) {
        return new Range(1, max).map((i) -> {
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
