package com.noodlesandwich.fizzbuzz;

import java.util.ArrayList;
import java.util.List;

public final class Lambdas {
    public static final Lambda Identity = x -> x;

    public static final Lambda True = x -> y -> x;
    public static final Lambda False = x -> y -> y;

    public static final Lambda Zero = f -> x -> x;
    public static final Lambda Succ = n -> f -> x -> f.call(n.call(f).call(x));
    public static final Lambda Pred = n -> f -> x -> n.call(g -> h -> h.call(g.call(f))).call(ignored -> x).call(u -> u);
    public static final Lambda IsZero = f -> f.call(x -> False).call(True);

    @SuppressWarnings("unused")
    public static final Lambda Y = f -> ((Lambda) (x -> f.call(x.call(x))))
                                               .call(x -> f.call(x.call(x)));
    public static final Lambda Z = f -> ((Lambda) (x -> f.call(y -> x.call(x).call(y))))
                                               .call(x -> f.call(y -> x.call(x).call(y)));

    public static final Lambda Add = x -> x.call(Succ);
    public static final Lambda Subtract = x -> y -> y.call(Pred).call(x);

    public static final Lambda IsLessOrEqual = x -> y -> IsZero.call(Subtract.call(x).call(y));

    public static final Lambda If = p -> t -> f -> p.call(t).call(f);

    public static final Lambda Multiply = x -> y -> y.call(Add.call(x)).call(Zero);
    public static final Lambda Divide = n -> d ->
            Z.call(_Divide -> q -> r ->
                If.call(IsLessOrEqual.call(d).call(r))
                  .call(z -> _Divide.call(Succ.call(q)).call(Subtract.call(r).call(d)).call(z))
                  .call(q)
            ).call(Zero).call(n);
    public static final Lambda Mod = Z.call(_Mod -> x -> y ->
            If.call(IsLessOrEqual.call(y).call(x))
              .call(n -> _Mod.call(Subtract.call(x).call(y)).call(y).call(n))
              .call(x));

    public static final Lambda Pair = a -> b -> f -> f.call(a).call(b);
    public static final Lambda First = p -> p.call(a -> b -> a);
    public static final Lambda Second = p -> p.call(a -> b -> b);

    public static final Lambda Nil = Pair.call(True).call(True);
    public static final Lambda Cons = h -> t -> Pair.call(False).call(Pair.call(h).call(t));
    public static final Lambda Head = z -> First.call(Second.call(z));
    public static final Lambda Tail = z -> Second.call(Second.call(z));
    public static final Lambda IsNil = First;

    public static final Lambda Fold = Z.call(_Fold -> f -> z -> l ->
            If.call(IsNil.call(l))
                    .call(z)
                    .call(x -> f.call(Head.call(l)).call(_Fold.call(f).call(z).call(Tail.call(l))).call(x)));
    public static final Lambda Append = l -> end -> Fold.call(Cons).call(Cons.call(end).call(Nil)).call(l);
    public static final Lambda Concat = Z.call(_Concat -> a -> b ->
            If.call(IsNil.call(a))
              .call(b)
              .call(Cons.call(Head.call(a)).call(x -> _Concat.call(Tail.call(a)).call(b).call(x))));
    public static final Lambda Index = Z.call(_Index -> i -> list ->
            If.call(IsZero.call(i))
              .call(Head.call(list))
              .call(z -> _Index.call(Pred.call(i)).call(Tail.call(list)).call(z)));

    public static final Lambda Range = Z.call(_Range -> a -> b ->
            If.call(IsLessOrEqual.call(b).call(a))
              .call(Nil)
              .call(Cons.call(a).call(x -> _Range.call(Succ.call(a)).call(b).call(x))));

    public static final Lambda Map = f -> Fold.call(h -> t -> Cons.call(f.call(h)).call(t)).call(Nil);

    public static Lambda fromInt(int integer) {
        if (integer == 0) {
            return Zero;
        }

        return Succ.call(fromInt(integer - 1));
    }

    public static int toInt(Lambda lambda) {
        Result<Integer> i = new Result<>(0);
        lambda
            .call(x -> {
                i.set(i.get() + 1);
                return x;
            })
            .call(Identity);
        return i.get();
    }

    public static Lambda fromList(List<Lambda> list) {
        if (list.isEmpty()) {
            return Nil;
        }

        Lambda head = list.get(0);
        List<Lambda> tail = list.subList(1, list.size());
        return Cons.call(head).call(fromList(tail));
    }

    public static List<Lambda> toList(Lambda lambda) {
        List<Lambda> result = new ArrayList<>();
        Z.call(_Loop -> l ->
            If.call(IsNil.call(l))
              .call(Nil)
              .call(x -> {
                  result.add(Head.call(l));
                  return _Loop.call(Tail.call(l)).call(x);
              }))
        .call(lambda)
        .call(Identity);
        return result;
    }

    public static boolean toBoolean(Lambda lambda) {
        Result<Boolean> result = new Result<>();
        lambda
            .call(x -> {
                result.set(true);
                return x;
            })
            .call(x -> {
                result.set(false);
                return x;
            })
            .call(Identity);
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
