package com.noodlesandwich.fizzbuzz;

import java.util.stream.Stream;

import static com.noodlesandwich.fizzbuzz.Lambdas.*;

public final class FizzBuzz {
    private static final Lambda One = Succ.call(Zero);
    private static final Lambda Three = Succ.call(Succ.call(Succ.call(Zero)));
    private static final Lambda Five = Succ.call(Succ.call(Succ.call(Succ.call(Succ.call(Zero)))));
    private static final Lambda Fifteen = Succ.call(Succ.call(Succ.call(Succ.call(Succ.call(Succ.call(Succ.call(Succ.call(Succ.call(Succ.call(Succ.call(Succ.call(Succ.call(Succ.call(Succ.call(Zero)))))))))))))));

    public static Stream<String> upTo(int max) {
        return toList(
            Map.call((i) ->
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
                )))
            ).call(Range.call(One).call(Succ.call(fromInt(max))))
        ).stream().map((lambda) -> ((Result<String>) lambda).value());
    }
}
