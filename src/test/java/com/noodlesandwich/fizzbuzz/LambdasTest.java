package com.noodlesandwich.fizzbuzz;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static com.noodlesandwich.fizzbuzz.Lambdas.*;

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
    modulus_works() {
        assertThat(toInt(Mod.call(fromInt(2)).call(fromInt(5))), is(2));
        assertThat(toInt(Mod.call(fromInt(18)).call(fromInt(7))), is(4));
    }

    @Test public void
    if_works() {
        Lambda x = aLambda();
        Lambda y = aLambda();
        assertThat(If.call(True).call(x).call(y), is(x));
        assertThat(If.call(False).call(x).call(y), is(y));
    }

    private static Lambda aLambda() {
        return (a) -> a;
    }
}
