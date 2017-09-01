/*

A law of Reverse

It's inefficient definition:

Nil.reverse = Nil                               // 1st clause
(x :: xs).reverse = xs.reverse ++ List(x)       // 2nd clause

Prove that:

xs.reverse.reverse = xs


Base case (Nil):

Nil.reverse.reverse
= Nil.reverse // 1st clause
= Nil         // 1st clause

Induction Step:
LHS
(x :: xs).reverse.reverse
= (xs.reverse ++ List(x)).reverse
= (xs.reverse ++ List(x)).reverse
RHS
x :: xs
= x :: xs.reverse.reverse // by induction hypthoesis

generalise
ys = xs.reverse
any list ys
simplifies to
(ys ++ List(x)).reverse = x :: ys.reverse




 */