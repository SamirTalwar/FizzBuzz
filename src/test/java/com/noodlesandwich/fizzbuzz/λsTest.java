package com.noodlesandwich.fizzbuzz;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.Test;

import static com.noodlesandwich.fizzbuzz.λs.Add;
import static com.noodlesandwich.fizzbuzz.λs.Append;
import static com.noodlesandwich.fizzbuzz.λs.Cons;
import static com.noodlesandwich.fizzbuzz.λs.Divide;
import static com.noodlesandwich.fizzbuzz.λs.False;
import static com.noodlesandwich.fizzbuzz.λs.Fold;
import static com.noodlesandwich.fizzbuzz.λs.Head;
import static com.noodlesandwich.fizzbuzz.λs.If;
import static com.noodlesandwich.fizzbuzz.λs.Index;
import static com.noodlesandwich.fizzbuzz.λs.IsLessOrEqual;
import static com.noodlesandwich.fizzbuzz.λs.IsNil;
import static com.noodlesandwich.fizzbuzz.λs.IsZero;
import static com.noodlesandwich.fizzbuzz.λs.Map;
import static com.noodlesandwich.fizzbuzz.λs.Mod;
import static com.noodlesandwich.fizzbuzz.λs.Multiply;
import static com.noodlesandwich.fizzbuzz.λs.Nil;
import static com.noodlesandwich.fizzbuzz.λs.Pred;
import static com.noodlesandwich.fizzbuzz.λs.Range;
import static com.noodlesandwich.fizzbuzz.λs.Subtract;
import static com.noodlesandwich.fizzbuzz.λs.Succ;
import static com.noodlesandwich.fizzbuzz.λs.Tail;
import static com.noodlesandwich.fizzbuzz.λs.True;
import static com.noodlesandwich.fizzbuzz.λs.Zero;
import static com.noodlesandwich.fizzbuzz.λs.fromInt;
import static com.noodlesandwich.fizzbuzz.λs.fromList;
import static com.noodlesandwich.fizzbuzz.λs.toBoolean;
import static com.noodlesandwich.fizzbuzz.λs.toInt;
import static com.noodlesandwich.fizzbuzz.λs.toList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;

public final class λsTest {
    private static final λ One = Succ.$(Zero);

    @Test public void
    numbers_work() {
        assertThat(toInt(Zero), is(0));
        assertThat(toInt(Succ.$(Zero)), is(1));
        assertThat(toInt(Succ.$(Succ.$(Zero))), is(2));
        assertThat(toInt(Succ.$(Succ.$(Succ.$(Succ.$(Succ.$(Zero)))))), is(5));

        assertThat(toInt(fromInt(10)), is(10));
    }

    @Test public void
    decrementing_works() {
        assertThat(toInt(Pred.$(fromInt(5))), is(4));
    }

    @Test public void
    numbers_stop_decrementing_at_zero() {
        assertThat(toInt(Pred.$(Zero)), is(0));
    }

    @Test public void
    Add_works() {
        assertThat(toInt(Add.$(Zero).$(Zero)), is(0));
        assertThat(toInt(Add.$(Zero).$(fromInt(3))), is(3));
        assertThat(toInt(Add.$(fromInt(42)).$(Zero)), is(42));
        assertThat(toInt(Add.$(fromInt(5)).$(fromInt(7))), is(12));
    }

    @Test public void
    Subtract_works() {
        assertThat(toInt(Subtract.$(Zero).$(Zero)), is(0));
        assertThat(toInt(Subtract.$(One).$(One)), is(0));
        assertThat(toInt(Subtract.$(fromInt(6)).$(Zero)), is(6));
        assertThat(toInt(Subtract.$(fromInt(9)).$(fromInt(5))), is(4));
        assertThat(toInt(Subtract.$(fromInt(4)).$(fromInt(12))), is(0));
        assertThat(toInt(Subtract.$(fromInt(100)).$(fromInt(99))), is(1));
    }

    @Test public void
    Multiply_works() {
        assertThat(toInt(Multiply.$(Zero).$(Zero)), is(0));
        assertThat(toInt(Multiply.$(Zero).$(fromInt(3))), is(0));
        assertThat(toInt(Multiply.$(fromInt(18)).$(Zero)), is(0));

        assertThat(toInt(Multiply.$(One).$(One)), is(1));
        assertThat(toInt(Multiply.$(One).$(fromInt(6))), is(6));
        assertThat(toInt(Multiply.$(fromInt(92)).$(One)), is(92));

        assertThat(toInt(Multiply.$(fromInt(5)).$(fromInt(7))), is(35));
        assertThat(toInt(Multiply.$(fromInt(12)).$(fromInt(45))), is(12 * 45));
    }

    @Test public void
    Divide_works() {
        assertThat(toInt(Divide.$(One).$(One)), is(1));
        assertThat(toInt(Divide.$(fromInt(120)).$(fromInt(120))), is(1));
        assertThat(toInt(Divide.$(fromInt(15)).$(One)), is(15));
        assertThat(toInt(Divide.$(fromInt(18)).$(fromInt(3))), is(6));
        assertThat(toInt(Divide.$(fromInt(100)).$(fromInt(10))), is(10));
    }

    @Test public void
    Mod_works() {
        assertThat(toInt(Mod.$(fromInt(2)).$(fromInt(5))), is(2));
        assertThat(toInt(Mod.$(fromInt(18)).$(fromInt(7))), is(4));
        assertThat(toInt(Mod.$(fromInt(100)).$(fromInt(10))), is(0));
    }

    @Test public void
    IsZero_works() {
        assertThat(toBoolean(IsZero.$(Zero)), is(true));
        assertThat(toBoolean(IsZero.$(One)), is(false));
        assertThat(toBoolean(IsZero.$(fromInt(2))), is(false));
    }

    @Test public void
    IsLessOrEqual_works() {
        assertThat(toBoolean(IsLessOrEqual.$(fromInt(5)).$(fromInt(7))), is(true));
        assertThat(toBoolean(IsLessOrEqual.$(fromInt(0)).$(fromInt(10))), is(true));

        assertThat(toBoolean(IsLessOrEqual.$(fromInt(3)).$(fromInt(3))), is(true));

        assertThat(toBoolean(IsLessOrEqual.$(fromInt(6)).$(fromInt(0))), is(false));
        assertThat(toBoolean(IsLessOrEqual.$(fromInt(4)).$(fromInt(2))), is(false));

        assertThat(toBoolean(IsLessOrEqual.$(fromInt(100)).$(fromInt(0))), is(false));
        assertThat(toBoolean(IsLessOrEqual.$(fromInt(0)).$(fromInt(100))), is(true));
    }

    @Test public void
    If_works() {
        λ x = aLambda();
        λ y = aLambda();
        assertThat(If.$(True).$(x).$(y), is(x));
        assertThat(If.$(False).$(x).$(y), is(y));
    }

    @Test public void
    Nil_works() {
        assertThat(toBoolean(IsNil.$(Nil)), is(true));
    }

    @Test public void
    Cons_works() {
        λ element = aLambda();
        λ list = Cons.$(element).$(Nil);
        assertThat(toBoolean(IsNil.$(list)), is(false));
        assertThat(Head.$(list), is(element));
        assertThat(toBoolean(IsNil.$(Tail.$(list))), is(true));
    }

    @SuppressWarnings("unchecked")
    @Test public void
    creates_a_list() {
        λ a = aLambda();
        λ b = aLambda();
        λ c = aLambda();
        λ list = Cons.$(a).$(Cons.$(b).$(Cons.$(c).$(Nil)));

        assertThat(toList(list), contains(a, b, c));
    }

    @SuppressWarnings("unchecked")
    @Test public void
    Range_works() {
        λ range = Range.$(fromInt(5)).$(fromInt(10));
        assertThat(integers(range), contains(5, 6, 7, 8, 9));
    }

    @SuppressWarnings("unchecked")
    @Test public void
    Map_works() {
        λ range = Range.$(fromInt(2)).$(fromInt(5));
        λ mappedRange = Map.$(Add.$(fromInt(4))).$(range);
        assertThat(integers(mappedRange), contains(6, 7, 8));
    }

    @Test public void
    Fold_works() {
        λ a = aLambda();
        λ b = aLambda();
        λ c = aLambda();
        assertThat(toList(Fold.$(Cons).$(Nil).$(fromList(Arrays.asList(a, b, c)))), contains(a, b, c));

        assertThat(toInt(Fold.$(Add).$(Zero).$(integers(Stream.of(6, 2, 9, 11, 4, 16)))), is(48));
    }

    @Test public void
    Index_works() {
        λ list = integers(Stream.of(6, 3, 2, 9, 1, 2, 4));
        assertThat(toInt(Index.$(fromInt(0)).$(list)), is(6));
        assertThat(toInt(Index.$(fromInt(2)).$(list)), is(2));
        assertThat(toInt(Index.$(fromInt(6)).$(list)), is(4));
    }

    @Test public void
    Append_works() {
        assertThat(integers(Append.$(Nil).$(fromInt(99))), contains(99));

        λ list = integers(Stream.of(6, 3, 2, 12, 9, 21));
        assertThat(integers(Append.$(list).$(fromInt(7))), contains(6, 3, 2, 12, 9, 21, 7));
    }

    private static λ aLambda() {
        return a -> a;
    }

    private λ integers(Stream<Integer> of) {
        return fromList(of.map(λs::fromInt).collect(Collectors.<λ>toList()));
    }

    private static Iterable<Integer> integers(λ lambda) {
        return toList(lambda).stream().map(λs::toInt).collect(Collectors.<Integer>toList());
    }
}
