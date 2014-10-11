package com.noodlesandwich.fizzbuzz;

import java.util.List;
import java.util.stream.Collectors;

import static com.noodlesandwich.fizzbuzz.Characters.NumberAsString;
import static com.noodlesandwich.fizzbuzz.Characters._B;
import static com.noodlesandwich.fizzbuzz.Characters._F;
import static com.noodlesandwich.fizzbuzz.Characters._i;
import static com.noodlesandwich.fizzbuzz.Characters._u;
import static com.noodlesandwich.fizzbuzz.Characters._z;
import static com.noodlesandwich.fizzbuzz.Conversions.fromInt;
import static com.noodlesandwich.fizzbuzz.Conversions.toList;
import static com.noodlesandwich.fizzbuzz.λs.Concat;
import static com.noodlesandwich.fizzbuzz.λs.Cons;
import static com.noodlesandwich.fizzbuzz.λs.If;
import static com.noodlesandwich.fizzbuzz.λs.IsZero;
import static com.noodlesandwich.fizzbuzz.λs.Map;
import static com.noodlesandwich.fizzbuzz.λs.Mod;
import static com.noodlesandwich.fizzbuzz.λs.Multiply;
import static com.noodlesandwich.fizzbuzz.λs.Nil;
import static com.noodlesandwich.fizzbuzz.λs.Range;
import static com.noodlesandwich.fizzbuzz.λs.Succ;
import static com.noodlesandwich.fizzbuzz.λs.Zero;

public final class FizzBuzz {
    private static final λ One = Succ.$(Zero);
    private static final λ Three = Succ.$(Succ.$(Succ.$(Zero)));
    private static final λ Five = Succ.$(Succ.$(Succ.$(Succ.$(Succ.$(Zero)))));
    private static final λ Fifteen = Multiply.$(Three).$(Five);

    private static final λ _Fizz = Cons.$(_F).$(Cons.$(_i).$(Cons.$(_z).$(Cons.$(_z).$(Nil))));
    private static final λ _Buzz = Cons.$(_B).$(Cons.$(_u).$(Cons.$(_z).$(Cons.$(_z).$(Nil))));
    private static final λ _FizzBuzz = Concat.$(_Fizz).$(_Buzz);

    public static List<String> upTo(int max) {
        return toList(upTo(fromInt(max))).stream().map(Conversions::toS).collect(Collectors.<String>toList());
    }

    public static λ upTo(λ max) {
        return Map.$(i ->
            If.$(IsZero.$(Mod.$(i).$(Fifteen)))
              .$(_FizzBuzz)
              .$(
            If.$(IsZero.$(Mod.$(i).$(Three)))
              .$(_Fizz)
              .$(
            If.$(IsZero.$(Mod.$(i).$(Five)))
              .$(_Buzz)
              .$(
            NumberAsString.$(i)
            )))
        ).$(Range.$(One).$(Succ.$(max)));
    }

    public static void main(String[] args) {
        int max = args.length > 0 ? Integer.parseInt(args[0]) : 100;
        System.out.println(upTo(max));
    }
}
