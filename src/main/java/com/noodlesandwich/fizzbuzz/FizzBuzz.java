package com.noodlesandwich.fizzbuzz;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.noodlesandwich.fizzbuzz.Characters.*;
import static com.noodlesandwich.fizzbuzz.Lambdas.*;

public final class FizzBuzz {
    private static final Lambda One = Succ.call(Zero);
    private static final Lambda Three = Succ.call(Succ.call(Succ.call(Zero)));
    private static final Lambda Five = Succ.call(Succ.call(Succ.call(Succ.call(Succ.call(Zero)))));
    private static final Lambda Fifteen = Multiply.call(Three).call(Five);

    private static final Lambda Fizz = fromList(Arrays.asList(_F, _i, _z, _z));
    private static final Lambda Buzz = fromList(Arrays.asList(_B, _u, _z, _z));
    private static final Lambda FizzBuzz = Concat.call(Fizz).call(Buzz);

    @SuppressWarnings("unchecked")
    public static List<String> upTo(int max) {
        return toList(
            Map.call(i ->
                If.call(IsZero.call(Mod.call(i).call(Fifteen)))
                  .call(FizzBuzz)
                  .call(
                If.call(IsZero.call(Mod.call(i).call(Three)))
                  .call(Fizz)
                  .call(
                If.call(IsZero.call(Mod.call(i).call(Five)))
                  .call(Buzz)
                  .call(
                NumberAsString.call(i)
                )))
            ).call(Range.call(One).call(Succ.call(fromInt(max))))
        ).stream().map(Characters::toS).collect(Collectors.<String>toList());
    }
}
