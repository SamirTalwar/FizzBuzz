package com.noodlesandwich.fizzbuzz;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import static com.noodlesandwich.fizzbuzz.λs.Cons;
import static com.noodlesandwich.fizzbuzz.λs.Head;
import static com.noodlesandwich.fizzbuzz.λs.Identity;
import static com.noodlesandwich.fizzbuzz.λs.If;
import static com.noodlesandwich.fizzbuzz.λs.IsNil;
import static com.noodlesandwich.fizzbuzz.λs.Nil;
import static com.noodlesandwich.fizzbuzz.λs.Succ;
import static com.noodlesandwich.fizzbuzz.λs.Tail;
import static com.noodlesandwich.fizzbuzz.λs.Z;
import static com.noodlesandwich.fizzbuzz.λs.Zero;

public final class Conversions {
    private static final char[] characters = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();

    private Conversions() { }

    public static boolean toBoolean(λ lambda) {
        AtomicBoolean result = new AtomicBoolean();
        lambda
                .$(x -> {
                    result.set(true);
                    return x;
                })
                .$(x -> {
                    result.set(false);
                    return x;
                })
                .$(Identity);
        return result.get();
    }

    public static λ fromInt(int integer) {
        if (integer == 0) {
            return Zero;
        }

        return Succ.$(fromInt(integer - 1));
    }

    public static int toInt(λ lambda) {
        AtomicInteger result = new AtomicInteger();
        lambda
            .$(x -> {
                result.set(result.get() + 1);
                return x;
            })
            .$(Identity);
        return result.get();
    }

    public static char toChar(λ c) {
        return characters[toInt(c)];
    }

    public static String toS(λ list) {
        List<λ> characters = toList(list);
        StringBuilder string = new StringBuilder();
        for (λ c : characters) {
            string.append(toChar(c));
        }
        return string.toString();
    }

    public static λ fromList(List<λ> list) {
        if (list.isEmpty()) {
            return Nil;
        }

        λ head = list.get(0);
        List<λ> tail = list.subList(1, list.size());
        return Cons.$(head).$(fromList(tail));
    }

    public static List<λ> toList(λ lambda) {
        List<λ> result = new ArrayList<>();
        Z.$(_Loop -> l ->
            If.$(IsNil.$(l))
              .$(Nil)
              .$(x -> {
                  result.add(Head.$(l));
                  return _Loop.$(Tail.$(l)).$(x);
              }))
        .$(lambda)
        .$(Identity);
        return result;
    }
}
