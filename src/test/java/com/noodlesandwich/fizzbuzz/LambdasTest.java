package com.noodlesandwich.fizzbuzz;

import java.util.stream.Stream;
import org.junit.Test;

import static com.noodlesandwich.fizzbuzz.Lambdas.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;

public final class LambdasTest {
    @Test public void
    numbers_work() {
        assertThat(toInt(Zero), is(0));
        assertThat(toInt(Succ.call(Zero)), is(1));
        assertThat(toInt(Succ.call(Succ.call(Zero))), is(2));
        assertThat(toInt(Succ.call(Succ.call(Succ.call(Succ.call(Succ.call(Zero)))))), is(5));

        assertThat(toInt(fromInt(10)), is(10));
    }

    @Test public void
    arithmetic_works() {
        assertThat(toInt(Add.call(fromInt(5)).call(fromInt(7))), is(12));
        assertThat(toInt(Subtract.call(fromInt(9)).call(fromInt(3))), is(6));
    }

    @Test public void
    Mod_works() {
        assertThat(toInt(Mod.call(fromInt(2)).call(fromInt(5))), is(2));
        assertThat(toInt(Mod.call(fromInt(18)).call(fromInt(7))), is(4));
    }

    @Test public void
    numbers_stop_decrementing_at_zero() {
        assertThat(toInt(Pred.call(Zero)), is(0));
    }

    @Test public void
    IsZero_works() {
        assertThat(toBoolean(IsZero.call(Zero)), is(true));
        assertThat(toBoolean(IsZero.call(fromInt(1))), is(false));
        assertThat(toBoolean(IsZero.call(fromInt(2))), is(false));
    }

    @Test public void
    If_works() {
        Lambda x = aLambda();
        Lambda y = aLambda();
        assertThat(If.call(True).call(x).call(y), is(x));
        assertThat(If.call(False).call(x).call(y), is(y));
    }

    @Test public void
    Nil_works() {
        assertThat(toBoolean(IsNil.call(Nil)), is(true));
    }

    @Test public void
    Cons_works() {
        Lambda element = aLambda();
        Lambda list = Cons.call(element).call(Nil);
        assertThat(toBoolean(IsNil.call(list)), is(false));
        assertThat(Head.call(list), is(element));
        assertThat(toBoolean(IsNil.call(Tail.call(list))), is(true));
    }

    @SuppressWarnings("unchecked")
    @Test public void
    creates_a_list() {
        Lambda a = aLambda();
        Lambda b = aLambda();
        Lambda c = aLambda();
        Lambda list = Cons.call(a).call(Cons.call(b).call(Cons.call(c).call(Nil)));

        assertThat(toList(list), contains(a, b, c));
    }

    @SuppressWarnings("unchecked")
    @Test public void
    Range_works() {
        Lambda range = Range.call(fromInt(5)).call(fromInt(10));
        assertThat(integers(range), contains(5, 6, 7, 8, 9));
    }

    @SuppressWarnings("unchecked")
    @Test public void
    Map_works() {
        Lambda range = Range.call(fromInt(2)).call(fromInt(5));
        assertThat(integers(Map.call(Add.call(fromInt(4))).call(range)),
                   contains(6, 7, 8));
    }

    private static Lambda aLambda() {
        return (a) -> a;
    }

    @SuppressWarnings("unchecked")
    private static boolean toBoolean(Lambda lambda) {
        return ((Result<Boolean>) (lambda.call(new Result<>(true)).call(new Result<>(false)))).value();
    }

    private static Iterable<Integer> integers(Lambda lambda) {
        Stream<Integer> stream = toList(lambda).stream().map(Lambdas::toInt);
        return stream::iterator;
    }
}
