package com.noodlesandwich.fizzbuzz;

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

public interface Characters {
    λ _0 = Zero;
    λ _1 = Succ.$(_0);
    λ _2 = Succ.$(_1);
    λ _3 = Succ.$(_2);
    λ _4 = Succ.$(_3);
    λ _5 = Succ.$(_4);
    λ _6 = Succ.$(_5);
    λ _7 = Succ.$(_6);
    λ _8 = Succ.$(_7);
    λ _9 = Succ.$(_8);

    λ _A = Succ.$(_9);
    λ _B = Succ.$(_A);
    λ _C = Succ.$(_B);
    λ _D = Succ.$(_C);
    λ _E = Succ.$(_D);
    λ _F = Succ.$(_E);
    λ _G = Succ.$(_F);
    λ _H = Succ.$(_G);
    λ _I = Succ.$(_H);
    λ _J = Succ.$(_I);
    λ _K = Succ.$(_J);
    λ _L = Succ.$(_K);
    λ _M = Succ.$(_L);
    λ _N = Succ.$(_M);
    λ _O = Succ.$(_N);
    λ _P = Succ.$(_O);
    λ _Q = Succ.$(_P);
    λ _R = Succ.$(_Q);
    λ _S = Succ.$(_R);
    λ _T = Succ.$(_S);
    λ _U = Succ.$(_T);
    λ _V = Succ.$(_U);
    λ _W = Succ.$(_V);
    λ _X = Succ.$(_W);
    λ _Y = Succ.$(_X);
    λ _Z = Succ.$(_Y);

    λ _a = Succ.$(_Z);
    λ _b = Succ.$(_a);
    λ _c = Succ.$(_b);
    λ _d = Succ.$(_c);
    λ _e = Succ.$(_d);
    λ _f = Succ.$(_e);
    λ _g = Succ.$(_f);
    λ _h = Succ.$(_g);
    λ _i = Succ.$(_h);
    λ _j = Succ.$(_i);
    λ _k = Succ.$(_j);
    λ _l = Succ.$(_k);
    λ _m = Succ.$(_l);
    λ _n = Succ.$(_m);
    λ _o = Succ.$(_n);
    λ _p = Succ.$(_o);
    λ _q = Succ.$(_p);
    λ _r = Succ.$(_q);
    λ _s = Succ.$(_r);
    λ _t = Succ.$(_s);
    λ _u = Succ.$(_t);
    λ _v = Succ.$(_u);
    λ _w = Succ.$(_v);
    λ _x = Succ.$(_w);
    λ _y = Succ.$(_x);
    λ _z = Succ.$(_y);

    λ Ten = Succ.$(_9);

    λ NumberAsChar = n -> Index.$(n).$(Range.$(Zero).$(Ten));

    λ NumberAsString = Z.$(_NumberAsString -> n ->
            If.$(IsLessOrEqual.$(n).$(_9))
              .$(Cons.$(NumberAsChar.$(n)).$(Nil))
              .$(z -> Append.$(_NumberAsString.$(Divide.$(n).$(Ten)))
                      .$(Mod.$(n).$(Ten))
                      .$(z)));
}
