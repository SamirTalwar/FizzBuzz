package com.noodlesandwich.fizzbuzz;

import java.util.Iterator;

import static com.noodlesandwich.fizzbuzz.Lambdas.*;

public final class FizzBuzz {
    private static final Lambda Three = Succ.call(Succ.call(Succ.call(Zero)));
    private static final Lambda Five = Succ.call(Succ.call(Succ.call(Succ.call(Succ.call(Zero)))));
    private static final Lambda Fifteen = Succ.call(Succ.call(Succ.call(Succ.call(Succ.call(Succ.call(Succ.call(Succ.call(Succ.call(Succ.call(Succ.call(Succ.call(Succ.call(Succ.call(Succ.call(Zero)))))))))))))));

    public static Iterable<String> upTo(int max) {
        return new Range(1, max).map(Lambdas::fromInt).map((i) -> {
            if (toBoolean(IsZero.call(fromInt(toInt(i) % toInt(Fifteen))))) {
                return "FizzBuzz";
            }
            if (toBoolean(IsZero.call(fromInt(toInt(i) % toInt(Three))))) {
                return "Fizz";
            }
            if (toBoolean(IsZero.call(fromInt(toInt(i) % toInt(Five))))) {
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
