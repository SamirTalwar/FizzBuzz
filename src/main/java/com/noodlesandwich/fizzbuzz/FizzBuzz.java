package com.noodlesandwich.fizzbuzz;

import java.util.Iterator;

import static com.noodlesandwich.fizzbuzz.Lambdas.*;

public final class FizzBuzz {
    private static final Lambda Three = Succ.call(Succ.call(Succ.call(Zero)));
    private static final Lambda Five = Succ.call(Succ.call(Succ.call(Succ.call(Succ.call(Zero)))));
    private static final Lambda Fifteen = Succ.call(Succ.call(Succ.call(Succ.call(Succ.call(Succ.call(Succ.call(Succ.call(Succ.call(Succ.call(Succ.call(Succ.call(Succ.call(Succ.call(Succ.call(Zero)))))))))))))));

    public static Iterable<String> upTo(int max) {
        return new Range(1, max).map(Lambdas::fromInt).map((i) -> {
            return
                If.call(IsZero.call(Mod.call(i).call(Fifteen)))
                  .call(new Result<>("FizzBuzz"))
                  .call(
                If.call(IsZero.call(Mod.call(i).call(Three)))
                  .call(new Result<>("Fizz"))
                  .call(
                If.call(IsZero.call(Mod.call(i).call(Five)))
                  .call(new Result<>("Buzz"))
                  .call(
                new Result<>(Integer.toString(toInt(i)))
                )));
        }).map((lambda) -> ((Result<String>) lambda).value());
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
