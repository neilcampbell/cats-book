package ch02

trait MyMonoid[A] extends MySemigroup[A] {
  def empty: A
}

object MyMonoid {
  def apply[A](implicit monoid: MyMonoid[A]) =
    monoid
}
