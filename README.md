FizzBuzz, Lambda Calculus-style
===============================

Inspired by [Tom Stuart's][@tomstuart] excellent talk on programming using just Ruby Procs entitled [Programming With Nothing][], I thought I'd try the same thing in Java. The idea is to remove all your language features bar two. Quoting Tom, "Naturally that means no gems [a.k.a. libraries&mdash;Samir], no standard library, no modules, methods, classes or objects. But this is love, so let’s go all the way: no control flow, no assignment, no arrays, strings, numbers or booleans." The two features we're going to keep are creating procs and calling procs.

We don't really have procs in Java, but we do have lambdas in Java 8, which are roughly the same (and exactly the same for the purposes of this exercise). So way back when, I grabbed the latest version of Project Lambda (which has since become part of Java 8) and gave implementing FizzBuzz (and, by extension, untyped lambda calculus) a crack. The results are pretty cool. Take a look at [λs.java][] for implementations of booleans, numbers, branching and lists, and [FizzBuzz.java][] for an idea of how they work.

I kept classes because, well, you have to in Java, but all methods are static and simply provide an entry point.

I hope this madness entertains you.

[@tomstuart]: http://twitter.com/tomstuart
[Programming With Nothing]: http://experthuman.com/programming-with-nothing

[FizzBuzz.java]: https://github.com/SamirTalwar/FizzBuzz/blob/master/src/main/java/com/noodlesandwich/fizzbuzz/FizzBuzz.java
[λs.java]: https://github.com/SamirTalwar/FizzBuzz/blob/master/src/main/java/com/noodlesandwich/fizzbuzz/λs.java
