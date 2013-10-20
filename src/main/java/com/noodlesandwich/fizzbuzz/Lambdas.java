package com.noodlesandwich.fizzbuzz;

import java.util.LinkedList;
import java.util.List;

public final class Lambdas {
    public static final Lambda True = x -> y -> x;
    public static final Lambda False = x -> y -> y;

    public static final Lambda Zero = f -> x -> x;
    public static final Lambda Succ = n -> f -> x -> f.call(n.call(f).call(x));
    public static final Lambda Pred = n -> f -> x -> n.call(g -> h -> h.call(g.call(f))).call(ignored -> x).call(u -> u);
    public static final Lambda IsZero = f -> f.call(x -> False).call(True);

    public static final Lambda Y = f -> ((Lambda) (x -> f.call(x.call(x))))
                                               .call(x -> f.call(x.call(x)));
    public static final Lambda Z = f -> ((Lambda) (x -> f.call(y -> x.call(x).call(y))))
                                               .call(x -> f.call(y -> x.call(x).call(y)));

    public static final Lambda Add = x -> x.call(Succ);
    public static final Lambda Subtract = x -> y -> y.call(Pred).call(x);
    public static final Lambda Multiply = x -> y -> y.call(Add.call(x)).call(Zero);
    public static final Lambda Mod = Z.call(_Mod -> x -> y ->
            IsZero.call(Subtract.call(y).call(x))
                  .call(n -> _Mod.call(Subtract.call(x).call(y)).call(y).call(n))
                  .call(x));

    public static final Lambda If = p -> t -> f -> p.call(t).call(f);

    public static final Lambda Pair = a -> b -> f -> f.call(a).call(b);
    public static final Lambda First = p -> p.call(a -> b -> a);
    public static final Lambda Second = p -> p.call(a -> b -> b);

    public static final Lambda Nil = Pair.call(True).call(True);
    public static final Lambda Cons = h -> t -> Pair.call(False).call(Pair.call(h).call(t));
    public static final Lambda Head = z -> First.call(Second.call(z));
    public static final Lambda Tail = z -> Second.call(Second.call(z));
    public static final Lambda IsNil = First;
    public static final Lambda Concat = Z.call(_Concat -> a -> b ->
            If.call(IsNil.call(a))
              .call(b)
              .call(Cons.call(Head.call(a)).call(x -> _Concat.call(Tail.call(a)).call(b).call(x))));

    public static final Lambda Range = Z.call(_Range -> a -> b ->
            If.call(IsZero.call(Subtract.call(b).call(a)))
              .call(Nil)
              .call(Cons.call(a).call(x -> _Range.call(Succ.call(a)).call(b).call(x))));

    public static final Lambda Fold = Z.call(_Fold -> f -> z -> l ->
            If.call(IsNil.call(l))
              .call(z)
              .call(f.call(Head.call(l)).call(x -> _Fold.call(f).call(z).call(Tail.call(l)).call(x))));

    public static final Lambda Map = f -> Fold.call(h -> t -> Cons.call(f.call(h)).call(t)).call(Nil);

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

        @SuppressWarnings("unchecked")
        public Lambda call(Lambda lambda) {
            return new Result<>(transformation.apply(((Result<T>) lambda).value()));
        }
    }

    public static Lambda fromInt(int value) {
        if (value == 0) {
            return Zero;
        }
        return Succ.call(fromInt(value - 1));
    }

    @SuppressWarnings("unchecked")
    public static int toInt(Lambda lambda) {
        Lambda result = lambda.call(new Transformation<Integer>(i -> i + 1)).call(new Result<>(0));
        return ((Result<Integer>) result.call(null)).value();
    }

    public static Lambda fromList(List<Lambda> list) {
        if (list.isEmpty()) {
            return Nil;
        }

        Lambda head = list.get(0);
        List<Lambda> tail = list.subList(1, list.size());
        return Cons.call(head).call(fromList(tail));
    }

    @SuppressWarnings("unchecked")
    public static List<Lambda> toList(Lambda lambda) {
        return ((Result<List<Lambda>>)
            If.call(IsNil.call(lambda))
              .call(new Result<List<Lambda>>(new LinkedList<>()))
              .call(x -> {
                  List<Lambda> list = toList(Tail.call(lambda));
                  list.add(0, Head.call(lambda));
                  return new Result<>(list);
              })
              .call(null)
          ).value();
    }
}
