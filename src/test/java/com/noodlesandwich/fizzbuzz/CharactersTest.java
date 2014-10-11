package com.noodlesandwich.fizzbuzz;

import org.junit.Test;

import static com.noodlesandwich.fizzbuzz.Characters.NumberAsChar;
import static com.noodlesandwich.fizzbuzz.Characters.NumberAsString;
import static com.noodlesandwich.fizzbuzz.Conversions.fromInt;
import static com.noodlesandwich.fizzbuzz.Conversions.toChar;
import static com.noodlesandwich.fizzbuzz.Conversions.toS;
import static com.noodlesandwich.fizzbuzz.λs.Zero;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public final class CharactersTest {
    @Test public void
    converts_integers_to_characters() {
        assertThat(toChar(NumberAsChar.$(Zero)), is('0'));
        assertThat(toChar(NumberAsChar.$(fromInt(1))), is('1'));
        assertThat(toChar(NumberAsChar.$(fromInt(7))), is('7'));
    }

    @Test public void
    converts_integers_to_strings() {
        assertThat(toS(NumberAsString.$(Zero)), is("0"));
        assertThat(toS(NumberAsString.$(fromInt(1))), is("1"));
        assertThat(toS(NumberAsString.$(fromInt(5))), is("5"));
        assertThat(toS(NumberAsString.$(fromInt(23))), is("23"));
        assertThat(toS(NumberAsString.$(fromInt(61))), is("61"));
        assertThat(toS(NumberAsString.$(fromInt(100))), is("100"));
        assertThat(toS(NumberAsString.$(fromInt(200))), is("200"));
    }
}
