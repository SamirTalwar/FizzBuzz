package com.noodlesandwich.fizzbuzz;

import java.util.List;

import static com.noodlesandwich.fizzbuzz.λs.Append;
import static com.noodlesandwich.fizzbuzz.λs.Cons;
import static com.noodlesandwich.fizzbuzz.λs.Divide;
import static com.noodlesandwich.fizzbuzz.λs.If;
import static com.noodlesandwich.fizzbuzz.λs.Index;
import static com.noodlesandwich.fizzbuzz.λs.IsLessOrEqual;
import static com.noodlesandwich.fizzbuzz.λs.Mod;
import static com.noodlesandwich.fizzbuzz.λs.Nil;
import static com.noodlesandwich.fizzbuzz.λs.Range;
import static com.noodlesandwich.fizzbuzz.λs.Succ;
import static com.noodlesandwich.fizzbuzz.λs.Z;
import static com.noodlesandwich.fizzbuzz.λs.Zero;
import static com.noodlesandwich.fizzbuzz.λs.toInt;
import static com.noodlesandwich.fizzbuzz.λs.toList;

public final class Characters {
    public static final λ _0 = Zero;
    public static final λ _1 = Succ.$(_0);
    public static final λ _2 = Succ.$(_1);
    public static final λ _3 = Succ.$(_2);
    public static final λ _4 = Succ.$(_3);
    public static final λ _5 = Succ.$(_4);
    public static final λ _6 = Succ.$(_5);
    public static final λ _7 = Succ.$(_6);
    public static final λ _8 = Succ.$(_7);
    public static final λ _9 = Succ.$(_8);

    public static final λ _A = Succ.$(_9);
    public static final λ _B = Succ.$(_A);
    public static final λ _C = Succ.$(_B);
    public static final λ _D = Succ.$(_C);
    public static final λ _E = Succ.$(_D);
    public static final λ _F = Succ.$(_E);
    public static final λ _G = Succ.$(_F);
    public static final λ _H = Succ.$(_G);
    public static final λ _I = Succ.$(_H);
    public static final λ _J = Succ.$(_I);
    public static final λ _K = Succ.$(_J);
    public static final λ _L = Succ.$(_K);
    public static final λ _M = Succ.$(_L);
    public static final λ _N = Succ.$(_M);
    public static final λ _O = Succ.$(_N);
    public static final λ _P = Succ.$(_O);
    public static final λ _Q = Succ.$(_P);
    public static final λ _R = Succ.$(_Q);
    public static final λ _S = Succ.$(_R);
    public static final λ _T = Succ.$(_S);
    public static final λ _U = Succ.$(_T);
    public static final λ _V = Succ.$(_U);
    public static final λ _W = Succ.$(_V);
    public static final λ _X = Succ.$(_W);
    public static final λ _Y = Succ.$(_X);
    public static final λ _Z = Succ.$(_Y);

    public static final λ _a = Succ.$(_Z);
    public static final λ _b = Succ.$(_a);
    public static final λ _c = Succ.$(_b);
    public static final λ _d = Succ.$(_c);
    public static final λ _e = Succ.$(_d);
    public static final λ _f = Succ.$(_e);
    public static final λ _g = Succ.$(_f);
    public static final λ _h = Succ.$(_g);
    public static final λ _i = Succ.$(_h);
    public static final λ _j = Succ.$(_i);
    public static final λ _k = Succ.$(_j);
    public static final λ _l = Succ.$(_k);
    public static final λ _m = Succ.$(_l);
    public static final λ _n = Succ.$(_m);
    public static final λ _o = Succ.$(_n);
    public static final λ _p = Succ.$(_o);
    public static final λ _q = Succ.$(_p);
    public static final λ _r = Succ.$(_q);
    public static final λ _s = Succ.$(_r);
    public static final λ _t = Succ.$(_s);
    public static final λ _u = Succ.$(_t);
    public static final λ _v = Succ.$(_u);
    public static final λ _w = Succ.$(_v);
    public static final λ _x = Succ.$(_w);
    public static final λ _y = Succ.$(_x);
    public static final λ _z = Succ.$(_y);

    private static final char[] characters = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();

    private static final λ Ten = Succ.$(_9);
    private static final λ Numbers = Range.$(Zero).$(Ten);

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

    public static final λ NumberAsChar = n -> Index.$(n).$(Numbers);

    public static final λ NumberAsString = Z.$(_NumberAsString -> n ->
            If.$(IsLessOrEqual.$(n).$(_9))
              .$(Cons.$(NumberAsChar.$(n)).$(Nil))
              .$(z -> Append.$(_NumberAsString.$(Divide.$(n).$(Ten)))
                      .$(Mod.$(n).$(Ten))
                      .$(z)));
}
