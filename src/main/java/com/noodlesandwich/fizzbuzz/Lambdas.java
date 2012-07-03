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

    public static interface Function<I, O> {
        O apply(I input);
    }

    public static final class Result<T> implements Lambda {
        private final T value;

        public Result(T value) {
            this.value = value;
        }

        public Lambda call(Lambda lambda) {
            return this;
        }

        public T value() {
            return value;
        }
    }

    public static final class Transformation<T> implements Lambda {
        private final Function<T, T> transformation;

        public Transformation(Function<T, T> transformation) {
            this.transformation = transformation;
        }

        public Lambda call(Lambda lambda) {
            return new Result<T>(transformation.apply(((Result<T>) lambda).value()));
        }
    }

    public static final Lambda fromInt(int value) {
        if (value == 0) {
            return Zero;
        }
        return Succ.call(fromInt(value - 1));
    }

    public static final int toInt(Lambda lambda) {
        return ((Result<Integer>) lambda.call(new Transformation<Integer>((i) -> i + 1)).call(new Result<>(0))).value();
    }

    public static final boolean toBoolean(Lambda lambda) {
        return ((Result<Boolean>) lambda.call(new Result<>(true)).call(new Result<>(false)).call(null)).value();
    }
}
