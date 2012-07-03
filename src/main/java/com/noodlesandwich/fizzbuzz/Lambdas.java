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
    public static final Lambda IsZero = (f) -> f.call((x) -> False).call(True);

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
