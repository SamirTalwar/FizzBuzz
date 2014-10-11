package com.noodlesandwich.fizzbuzz;

public final class λs {
    private λs() { }

    public static final λ Identity = x -> x;

    public static final λ True = x -> y -> x;
    public static final λ False = x -> y -> y;

    public static final λ Zero = f -> x -> x;
    public static final λ Succ = n -> f -> x -> f.$(n.$(f).$(x));
    public static final λ Pred = n -> f -> x -> n.$(g -> h -> h.$(g.$(f))).$(ignored -> x).$(u -> u);
    public static final λ IsZero = f -> f.$(x -> False).$(True);

    @SuppressWarnings("unused")
    public static final λ Y = f ->
            λ(x -> f.$(x.$(x)))
                .$(x -> f.$(x.$(x)));
    public static final λ Z = f ->
            λ(x -> f.$(y -> x.$(x).$(y)))
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

    // I'm so sorry. I had to do it.
    private static λ λ(λ λ) {
        return λ;
    }
}
