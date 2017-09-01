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

generalise (or substitution)
ys = xs.reverse
any list ys
simplifies to
(ys ++ List(x)).reverse = x :: ys.reverse

Auxiliary Equation, Base Case (ys = Nil)
(Nil ++ List(x)).reverse        // to show: = x :: Nil.reverse
= List(x).reverse
= (x :: NIl).reverse
= Nil.reverse ++ List(x)
Nil ++ (x :: Nil)  // by 1st clause of ++
= x :: Nil          // by 1st clause of reverse






Last Question in 5.7:
Equational proofs of functional programs

Show that:
(xs ++ ys) map f = (xs map f) ++ (ys map f)

clauses:
1. Nil map f = Nil
2. (x :: xs) map f = f(x) :: (xs map f)

concat clauses:
Laws of Concat

1. (xs ++ ys) ++ zs = xs ++ (ys ++ zs)
2. xs ++ Nil = xs
3. Nil ++ xs = xs




For Base case (Nil):
LHS
(xs ++ ys) map f
= (Nil ++ Nil) map f
= Nil map f
= Nil

RHS
(xs map f) ++ (ys map f)
= (Nil map f) ++ (Nil map f)
= Nil ++ Nil
= Nil = LHS

Induction Step:
LHS // should write rules used
(xs ++ ys) map f
= (x :: xs ++ y :: ys) map f

RHS
(xs map f) ++ (ys map f)
= (x :: xs) map f ++ (y :: ys) map f
// sub ts = x :: xs, and ws = y :: ys
= (ts map f) ++ (ws map f)
// assume hypothesis is true, that might be worded differently
= (ts ++ ws) map f
// sub back
= (x :: xs ++ y :: ys) map f = RHS
 */