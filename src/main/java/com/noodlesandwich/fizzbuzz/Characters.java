package com.noodlesandwich.fizzbuzz;

import java.util.stream.Collectors;

import static com.noodlesandwich.fizzbuzz.Lambdas.*;

public final class Characters {
    public static final Lambda _0 = Zero;
    public static final Lambda _1 = Succ.call(_0);
    public static final Lambda _2 = Succ.call(_1);
    public static final Lambda _3 = Succ.call(_2);
    public static final Lambda _4 = Succ.call(_3);
    public static final Lambda _5 = Succ.call(_4);
    public static final Lambda _6 = Succ.call(_5);
    public static final Lambda _7 = Succ.call(_6);
    public static final Lambda _8 = Succ.call(_7);
    public static final Lambda _9 = Succ.call(_8);

    public static final Lambda _A = Succ.call(_9);
    public static final Lambda _B = Succ.call(_A);
    public static final Lambda _C = Succ.call(_B);
    public static final Lambda _D = Succ.call(_C);
    public static final Lambda _E = Succ.call(_D);
    public static final Lambda _F = Succ.call(_E);
    public static final Lambda _G = Succ.call(_F);
    public static final Lambda _H = Succ.call(_G);
    public static final Lambda _I = Succ.call(_H);
    public static final Lambda _J = Succ.call(_I);
    public static final Lambda _K = Succ.call(_J);
    public static final Lambda _L = Succ.call(_K);
    public static final Lambda _M = Succ.call(_L);
    public static final Lambda _N = Succ.call(_M);
    public static final Lambda _O = Succ.call(_N);
    public static final Lambda _P = Succ.call(_O);
    public static final Lambda _Q = Succ.call(_P);
    public static final Lambda _R = Succ.call(_Q);
    public static final Lambda _S = Succ.call(_R);
    public static final Lambda _T = Succ.call(_S);
    public static final Lambda _U = Succ.call(_T);
    public static final Lambda _V = Succ.call(_U);
    public static final Lambda _W = Succ.call(_V);
    public static final Lambda _X = Succ.call(_W);
    public static final Lambda _Y = Succ.call(_X);
    public static final Lambda _Z = Succ.call(_Y);

    public static final Lambda _a = Succ.call(_Z);
    public static final Lambda _b = Succ.call(_a);
    public static final Lambda _c = Succ.call(_b);
    public static final Lambda _d = Succ.call(_c);
    public static final Lambda _e = Succ.call(_d);
    public static final Lambda _f = Succ.call(_e);
    public static final Lambda _g = Succ.call(_f);
    public static final Lambda _h = Succ.call(_g);
    public static final Lambda _i = Succ.call(_h);
    public static final Lambda _j = Succ.call(_i);
    public static final Lambda _k = Succ.call(_j);
    public static final Lambda _l = Succ.call(_k);
    public static final Lambda _m = Succ.call(_l);
    public static final Lambda _n = Succ.call(_m);
    public static final Lambda _o = Succ.call(_n);
    public static final Lambda _p = Succ.call(_o);
    public static final Lambda _q = Succ.call(_p);
    public static final Lambda _r = Succ.call(_q);
    public static final Lambda _s = Succ.call(_r);
    public static final Lambda _t = Succ.call(_s);
    public static final Lambda _u = Succ.call(_t);
    public static final Lambda _v = Succ.call(_u);
    public static final Lambda _w = Succ.call(_v);
    public static final Lambda _x = Succ.call(_w);
    public static final Lambda _y = Succ.call(_x);
    public static final Lambda _z = Succ.call(_y);

    private static final char[] characters = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();

    public static char toChar(Lambda c) {
        return characters[toInt(c)];
    }

    public static String toS(Lambda list) {
        return toList(list).stream().map(c -> String.valueOf(toChar(c))).collect(Collectors.joining());
    }
}
