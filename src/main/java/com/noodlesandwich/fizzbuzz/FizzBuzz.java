package com.noodlesandwich.fizzbuzz;

import static com.noodlesandwich.fizzbuzz.Lambdas.*;

public final class FizzBuzz {
    private static final Lambda One = Succ.call(Zero);
    private static final Lambda Three = Succ.call(Succ.call(Succ.call(Zero)));
    private static final Lambda Five = Succ.call(Succ.call(Succ.call(Succ.call(Succ.call(Zero)))));
    private static final Lambda Fifteen = Succ.call(Succ.call(Succ.call(Succ.call(Succ.call(Succ.call(Succ.call(Succ.call(Succ.call(Succ.call(Succ.call(Succ.call(Succ.call(Succ.call(Succ.call(Zero)))))))))))))));

    public static Iterable<String> upTo(int max) {
        return toIterable(Range.call(One).call(Succ.call(fromInt(max)))).map((i) -> {
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
}
