package com.noodlesandwich.fizzbuzz;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static com.noodlesandwich.fizzbuzz.Characters.*;
import static com.noodlesandwich.fizzbuzz.Lambdas.*;

public final class CharactersTest {
    @Test public void
    converts_integers_to_characters() {
        assertThat(toChar(NumberAsChar.call(Zero)), is('0'));
        assertThat(toChar(NumberAsChar.call(fromInt(1))), is('1'));
        assertThat(toChar(NumberAsChar.call(fromInt(7))), is('7'));
    }

    @Test public void
    converts_integers_to_strings() {
        assertThat(toS(NumberAsString.call(Zero)), is("0"));
        assertThat(toS(NumberAsString.call(fromInt(1))), is("1"));
        assertThat(toS(NumberAsString.call(fromInt(5))), is("5"));
        assertThat(toS(NumberAsString.call(fromInt(23))), is("23"));
        assertThat(toS(NumberAsString.call(fromInt(61))), is("61"));
    }
}
