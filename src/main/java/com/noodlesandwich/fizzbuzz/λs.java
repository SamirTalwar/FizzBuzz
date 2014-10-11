package com.noodlesandwich.fizzbuzz;

import static com.noodlesandwich.fizzbuzz.λ.λ;

public interface λs {
    λ Identity = x -> x;

    λ True = x -> y -> x;
    λ False = x -> y -> y;

    λ Zero = f -> x -> x;
    λ Succ = n -> f -> x -> f.$(n.$(f).$(x));
    λ Pred = n -> f -> x -> n.$(g -> h -> h.$(g.$(f))).$(ignored -> x).$(u -> u);
    λ IsZero = f -> f.$(x -> False).$(True);

    @SuppressWarnings("unused")
    λ Y = f ->
            λ(x -> f.$(x.$(x)))
                .$(x -> f.$(x.$(x)));
    λ Z = f ->
            λ(x -> f.$(y -> x.$(x).$(y)))
                .$(x -> f.$(y -> x.$(x).$(y)));

    λ Add = x -> x.$(Succ);
    λ Subtract = x -> y -> y.$(Pred).$(x);

    λ IsLessOrEqual = x -> y -> IsZero.$(Subtract.$(x).$(y));

    λ If = p -> t -> f -> p.$(t).$(f);

    λ Multiply = x -> y -> y.$(Add.$(x)).$(Zero);
    λ Divide = n -> d ->
            Z.$(_Divide -> q -> r ->
                    If.$(IsLessOrEqual.$(d).$(r))
                      .$(z -> _Divide.$(Succ.$(q)).$(Subtract.$(r).$(d)).$(z))
                      .$(q)
            ).$(Zero).$(n);
    λ Mod = Z.$(_Mod -> x -> y ->
            If.$(IsLessOrEqual.$(y).$(x))
              .$(n -> _Mod.$(Subtract.$(x).$(y)).$(y).$(n))
              .$(x));

    λ Pair = a -> b -> f -> f.$(a).$(b);
    λ First = p -> p.$(a -> b -> a);
    λ Second = p -> p.$(a -> b -> b);

    λ Nil = Pair.$(True).$(True);
    λ Cons = h -> t -> Pair.$(False).$(Pair.$(h).$(t));
    λ Head = z -> First.$(Second.$(z));
    λ Tail = z -> Second.$(Second.$(z));
    λ IsNil = First;

    λ Fold = Z.$(_Fold -> f -> z -> l ->
            If.$(IsNil.$(l))
              .$(z)
              .$(x -> f.$(Head.$(l)).$(_Fold.$(f).$(z).$(Tail.$(l))).$(x)));
    λ Append = l -> end -> Fold.$(Cons).$(Cons.$(end).$(Nil)).$(l);
    λ Concat = Z.$(_Concat -> a -> b ->
            If.$(IsNil.$(a))
              .$(b)
              .$(Cons.$(Head.$(a)).$(x -> _Concat.$(Tail.$(a)).$(b).$(x))));
    λ Index = Z.$(_Index -> i -> list ->
            If.$(IsZero.$(i))
              .$(Head.$(list))
              .$(z -> _Index.$(Pred.$(i)).$(Tail.$(list)).$(z)));

    λ Range = Z.$(_Range -> a -> b ->
            If.$(IsLessOrEqual.$(b).$(a))
              .$(Nil)
              .$(Cons.$(a).$(x -> _Range.$(Succ.$(a)).$(b).$(x))));

    λ Map = f -> Fold.$(h -> t -> Cons.$(f.$(h)).$(t)).$(Nil);
}
