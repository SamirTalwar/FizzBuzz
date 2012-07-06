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
    }
}
