package com.noodlesandwich.fizzbuzz;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.Test;

import static com.noodlesandwich.fizzbuzz.Lambdas.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;

public final class LambdasTest {
    private static final Lambda One = Succ.call(Zero);

    @Test public void
    numbers_work() {
        assertThat(toInt(Zero), is(0));
        assertThat(toInt(Succ.call(Zero)), is(1));
        assertThat(toInt(Succ.call(Succ.call(Zero))), is(2));
        assertThat(toInt(Succ.call(Succ.call(Succ.call(Succ.call(Succ.call(Zero)))))), is(5));

        assertThat(toInt(fromInt(10)), is(10));
    }

    @Test public void
    decrementing_works() {
        assertThat(toInt(Pred.call(fromInt(5))), is(4));
    }

    @Test public void
    numbers_stop_decrementing_at_zero() {
        assertThat(toInt(Pred.call(Zero)), is(0));
    }

    @Test public void
    Add_works() {
        assertThat(toInt(Add.call(Zero).call(Zero)), is(0));
        assertThat(toInt(Add.call(Zero).call(fromInt(3))), is(3));
        assertThat(toInt(Add.call(fromInt(42)).call(Zero)), is(42));
        assertThat(toInt(Add.call(fromInt(5)).call(fromInt(7))), is(12));
    }

    @Test public void
    Subtract_works() {
        assertThat(toInt(Subtract.call(Zero).call(Zero)), is(0));
        assertThat(toInt(Subtract.call(One).call(One)), is(0));
        assertThat(toInt(Subtract.call(fromInt(6)).call(Zero)), is(6));
        assertThat(toInt(Subtract.call(fromInt(9)).call(fromInt(5))), is(4));
        assertThat(toInt(Subtract.call(fromInt(4)).call(fromInt(12))), is(0));
        assertThat(toInt(Subtract.call(fromInt(100)).call(fromInt(99))), is(1));
    }

    @Test public void
    Multiply_works() {
        assertThat(toInt(Multiply.call(Zero).call(Zero)), is(0));
        assertThat(toInt(Multiply.call(Zero).call(fromInt(3))), is(0));
        assertThat(toInt(Multiply.call(fromInt(18)).call(Zero)), is(0));

        assertThat(toInt(Multiply.call(One).call(One)), is(1));
        assertThat(toInt(Multiply.call(One).call(fromInt(6))), is(6));
        assertThat(toInt(Multiply.call(fromInt(92)).call(One)), is(92));

        assertThat(toInt(Multiply.call(fromInt(5)).call(fromInt(7))), is(35));
        assertThat(toInt(Multiply.call(fromInt(12)).call(fromInt(45))), is(12 * 45));
    }

    @Test public void
    Divide_works() {
        assertThat(toInt(Divide.call(One).call(One)), is(1));
        assertThat(toInt(Divide.call(fromInt(120)).call(fromInt(120))), is(1));
        assertThat(toInt(Divide.call(fromInt(15)).call(One)), is(15));
        assertThat(toInt(Divide.call(fromInt(18)).call(fromInt(3))), is(6));
    }

    @Test public void
    Mod_works() {
        assertThat(toInt(Mod.call(fromInt(2)).call(fromInt(5))), is(2));
        assertThat(toInt(Mod.call(fromInt(18)).call(fromInt(7))), is(4));
    }

    @Test public void
    IsZero_works() {
        assertThat(toBoolean(IsZero.call(Zero)), is(true));
        assertThat(toBoolean(IsZero.call(One)), is(false));
        assertThat(toBoolean(IsZero.call(fromInt(2))), is(false));
    }

    @Test public void
    IsLessOrEqual_works() {
        assertThat(toBoolean(IsLessOrEqual.call(fromInt(5)).call(fromInt(7))), is(true));
        assertThat(toBoolean(IsLessOrEqual.call(fromInt(0)).call(fromInt(10))), is(true));

        assertThat(toBoolean(IsLessOrEqual.call(fromInt(3)).call(fromInt(3))), is(true));

        assertThat(toBoolean(IsLessOrEqual.call(fromInt(6)).call(fromInt(0))), is(false));
        assertThat(toBoolean(IsLessOrEqual.call(fromInt(4)).call(fromInt(2))), is(false));

        assertThat(toBoolean(IsLessOrEqual.call(fromInt(100)).call(fromInt(0))), is(false));
        assertThat(toBoolean(IsLessOrEqual.call(fromInt(0)).call(fromInt(100))), is(true));
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
        Lambda mappedRange = Map.call(Add.call(fromInt(4))).call(range);
        assertThat(integers(mappedRange), contains(6, 7, 8));
    }

    @Test public void
    Fold_works() {
        Lambda a = aLambda();
        Lambda b = aLambda();
        Lambda c = aLambda();
        assertThat(toList(Fold.call(Cons).call(Nil).call(fromList(Arrays.asList(a, b, c)))), contains(a, b, c));

        assertThat(toInt(Fold.call(Add).call(Zero).call(integers(Stream.of(6, 2, 9, 11, 4, 16)))), is(48));
    }

    @Test public void
    Index_works() {
        Lambda list = integers(Stream.of(6, 3, 2, 9, 1, 2, 4));
        assertThat(toInt(Index.call(fromInt(0)).call(list)), is(6));
        assertThat(toInt(Index.call(fromInt(2)).call(list)), is(2));
        assertThat(toInt(Index.call(fromInt(6)).call(list)), is(4));
    }

    private static Lambda aLambda() {
        return a -> a;
    }

    private Lambda integers(Stream<Integer> of) {
        return fromList(of.map(Lambdas::fromInt).collect(Collectors.<Lambda>toList()));
    }

    private static Iterable<Integer> integers(Lambda lambda) {
        return toList(lambda).stream().map(Lambdas::toInt).collect(Collectors.<Integer>toList());
    }
}
