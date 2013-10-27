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

    private static final Lambda _Fizz = fromList(Arrays.asList(_F, _i, _z, _z));
    private static final Lambda _Buzz = fromList(Arrays.asList(_B, _u, _z, _z));
    private static final Lambda _FizzBuzz = Concat.call(_Fizz).call(_Buzz);

    public static List<String> upTo(int max) {
        return toList(upTo(fromInt(max))).stream().map(Characters::toS).collect(Collectors.<String>toList());
    }

    public static Lambda upTo(Lambda max) {
        return Map.call(i ->
            If.call(IsZero.call(Mod.call(i).call(Fifteen)))
              .call(_FizzBuzz)
              .call(
            If.call(IsZero.call(Mod.call(i).call(Three)))
              .call(_Fizz)
              .call(
            If.call(IsZero.call(Mod.call(i).call(Five)))
              .call(_Buzz)
              .call(
            NumberAsString.call(i)
            )))
        ).call(Range.call(One).call(Succ.call(max)));
    }

    public static void main(String[] args) {
        int max = args.length > 0 ? Integer.parseInt(args[0]) : 100;
        System.out.println(FizzBuzz.upTo(max));
    }
}
