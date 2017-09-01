/*


Laws of Concat


(xs ++ ys) ++ zs = xs ++ (ys ++ zs)
 xs ++ Nil = xs
 Nil ++ xs = xs


Natural Induction proof

P(n)

def factorial(n: Int): Int =
  if (n == 0) 1           // 1st clause
  else n * factorial(n-1) // 2nd clause


Show that, for all n >= 4

  factorial(n) >= power(2,n)
  2^n

  factorial(4) = 24 > 16 = power(2,4)

Show that for all n+1
  assume factorial(n) >= 2^n
  factorial(n+1)
  >= (n + 1)* factorial(n) // 2nd clause
  > 2 * factorial(n) // by calculating
  >= 2 * power(2,n) = power(2,n+1)

  Note that a proof can freely apply reduction steps as equalities to some part of a term.

  referential transparency

 */


/*

Structural Induction
show that P(Nil) holds base case
xs x
if p(xs) holds, then  p(x :: xs) also holds

Base case: Nil

(Nil ++ ys) ++ zs
 = ys ++ zs // by 1st clause of ++

 For the RHS, we have:
 Nil ++ (ys ++ zs)
 = ys ++ zs // by 1st clause of ++
 // This case is therefore established.

Induction step: x :: xs
For the LHS, we have:

((x :: xs) ++ ys) ++ zs
= (x :: ( xs ++ ys)) ++ zs // by the 2nd clause of ++
= x :: ((xs ++ ys) ++ zs) // by the 2nd clause of ++

RHS Induction step:

(x :: xs) ++ (ys ++ zs)
= x :: (xs ++ (ys ++ zs)) // by the 2nd clause of ++
So this case ( and with it, the property) is established.


Exercise:
xs ++ Nil = xs
Base case: xs = Nil
Nil ++ Nil
= Nil // by 1st clause
Induction step: x :: xs
(x :: xs) ++ Nil = x :: xs
=  x :: xs ++ Nil // 2nd clause
= x :: xs // by the induction hypothesis, case established

 */