package com.noodlesandwich.fizzbuzz;

import java.util.concurrent.atomic.*;

public final class Lambdas {
    public static interface Lambda {
        Lambda call(Lambda value);
    }

    public static final Lambda True = (x) -> (y) -> x;
    public static final Lambda False = (x) -> (y) -> y;

    public static final Lambda Zero = (f) -> (x) -> x;
    public static final Lambda Succ = (n) -> (f) -> (x) -> f.call(n.call(f).call(x));
    public static final Lambda Pred = (n) -> (f) -> (x) -> n.call((g) -> (h) -> h.call(g.call(f))).call((_) -> x).call((u) -> u);
    public static final Lambda IsZero = (f) -> f.call((x) -> False).call(True);

    public static final Lambda Add = (x) -> x.call(Succ);
    public static final Lambda Subtract = (x) -> (y) -> y.call(Pred).call(x);
    public static final Lambda Mod = (x) -> (y) -> IsZero.call(Subtract.call(y).call(x))
                                                         .call((n) -> Mod.call(Subtract.call(x).call(y)).call(y).call(n))
                                                         .call(x);

    public static final Lambda fromInt(int value) {
        if (value == 0) {
            return Zero;
        }
        return Succ.call(fromInt(value - 1));
    }

    public static final int toInt(Lambda lambda) {
        final AtomicInteger value = new AtomicInteger(0);
        lambda.call((_) -> { value.getAndIncrement(); return null; }).call(null);
        return value.get();
    }

    public static final boolean toBoolean(Lambda lambda) {
        final AtomicBoolean value = new AtomicBoolean();
        lambda.call((_) -> { value.set(true); return null; }).call((_) -> { value.set(false); return null; }).call(null);
        return value.get();
    }
}
