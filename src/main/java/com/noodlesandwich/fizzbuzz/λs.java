package com.noodlesandwich.fizzbuzz;

import java.util.ArrayList;
import java.util.List;

public final class λs {
    public static final λ Identity = x -> x;

    public static final λ True = x -> y -> x;
    public static final λ False = x -> y -> y;

    public static final λ Zero = f -> x -> x;
    public static final λ Succ = n -> f -> x -> f.$(n.$(f).$(x));
    public static final λ Pred = n -> f -> x -> n.$(g -> h -> h.$(g.$(f))).$(ignored -> x).$(u -> u);
    public static final λ IsZero = f -> f.$(x -> False).$(True);

    @SuppressWarnings("unused")
    public static final λ Y = f ->
            ((λ) (x -> f.$(x.$(x))))
                    .$(x -> f.$(x.$(x)));
    public static final λ Z = f ->
            ((λ) (x -> f.$(y -> x.$(x).$(y))))
                    .$(x -> f.$(y -> x.$(x).$(y)));

    public static final λ Add = x -> x.$(Succ);
    public static final λ Subtract = x -> y -> y.$(Pred).$(x);

    public static final λ IsLessOrEqual = x -> y -> IsZero.$(Subtract.$(x).$(y));

    public static final λ If = p -> t -> f -> p.$(t).$(f);

    public static final λ Multiply = x -> y -> y.$(Add.$(x)).$(Zero);
    public static final λ Divide = n -> d ->
            Z.$(_Divide -> q -> r ->
                    If.$(IsLessOrEqual.$(d).$(r))
                      .$(z -> _Divide.$(Succ.$(q)).$(Subtract.$(r).$(d)).$(z))
                      .$(q)
            ).$(Zero).$(n);
    public static final λ Mod = Z.$(_Mod -> x -> y ->
            If.$(IsLessOrEqual.$(y).$(x))
              .$(n -> _Mod.$(Subtract.$(x).$(y)).$(y).$(n))
              .$(x));

    public static final λ Pair = a -> b -> f -> f.$(a).$(b);
    public static final λ First = p -> p.$(a -> b -> a);
    public static final λ Second = p -> p.$(a -> b -> b);

    public static final λ Nil = Pair.$(True).$(True);
    public static final λ Cons = h -> t -> Pair.$(False).$(Pair.$(h).$(t));
    public static final λ Head = z -> First.$(Second.$(z));
    public static final λ Tail = z -> Second.$(Second.$(z));
    public static final λ IsNil = First;

    public static final λ Fold = Z.$(_Fold -> f -> z -> l ->
            If.$(IsNil.$(l))
              .$(z)
              .$(x -> f.$(Head.$(l)).$(_Fold.$(f).$(z).$(Tail.$(l))).$(x)));
    public static final λ Append = l -> end -> Fold.$(Cons).$(Cons.$(end).$(Nil)).$(l);
    public static final λ Concat = Z.$(_Concat -> a -> b ->
            If.$(IsNil.$(a))
              .$(b)
              .$(Cons.$(Head.$(a)).$(x -> _Concat.$(Tail.$(a)).$(b).$(x))));
    public static final λ Index = Z.$(_Index -> i -> list ->
            If.$(IsZero.$(i))
              .$(Head.$(list))
              .$(z -> _Index.$(Pred.$(i)).$(Tail.$(list)).$(z)));

    public static final λ Range = Z.$(_Range -> a -> b ->
            If.$(IsLessOrEqual.$(b).$(a))
              .$(Nil)
              .$(Cons.$(a).$(x -> _Range.$(Succ.$(a)).$(b).$(x))));

    public static final λ Map = f -> Fold.$(h -> t -> Cons.$(f.$(h)).$(t)).$(Nil);

    public static λ fromInt(int integer) {
        if (integer == 0) {
            return Zero;
        }

        return Succ.$(fromInt(integer - 1));
    }

    public static int toInt(λ lambda) {
        Result<Integer> i = new Result<>(0);
        lambda
            .$(x -> {
                i.set(i.get() + 1);
                return x;
            })
            .$(Identity);
        return i.get();
    }

    public static λ fromList(List<λ> list) {
        if (list.isEmpty()) {
            return Nil;
        }

        λ head = list.get(0);
        List<λ> tail = list.subList(1, list.size());
        return Cons.$(head).$(fromList(tail));
    }

    public static List<λ> toList(λ lambda) {
        List<λ> result = new ArrayList<>();
        Z.$(_Loop -> l ->
                If.$(IsNil.$(l))
                        .$(Nil)
                        .$(x -> {
                            result.add(Head.$(l));
                            return _Loop.$(Tail.$(l)).$(x);
                        }))
        .$(lambda)
        .$(Identity);
        return result;
    }

    public static boolean toBoolean(λ lambda) {
        Result<Boolean> result = new Result<>();
        lambda
            .$(x -> {
                result.set(true);
                return x;
            })
            .$(x -> {
                result.set(false);
                return x;
            })
            .$(Identity);
        return result.get();
    }

    public static final class Result<T> {
        private T value = null;
        private boolean set = false;

        public Result() { }

        public Result(T value) {
            set(value);
        }

        public T get() {
            if (!set) {
                throw new IllegalStateException("Value was never set.");
            }
            return value;
        }

        public void set(T value) {
            this.value = value;
            this.set = true;
        }
    }
}
