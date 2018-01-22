package ch02

  object Laws {
    def associativeLaw[A](x: A, y: A, z: A)
                         (implicit m: MyMonoid[A]): Boolean = {
      m.combine(x, m.combine(y, z)) ==
        m.combine(m.combine(x, y), z)
    }
    def identityLaw[A](x: A)
                      (implicit m: MyMonoid[A]): Boolean = {
      (m.combine(x, m.empty) == x) &&
        (m.combine(m.empty, x) == x)
    }
  }


  object And {
    implicit val booleanAndMonoid: MyMonoid[Boolean] =
      new MyMonoid[Boolean] {
        def combine(a: Boolean, b: Boolean) = a && b
        def empty = true
      }

    def test: Unit = {
      println(Laws.associativeLaw(true, false, false))
      println(Laws.identityLaw(true))
    }
  }

object Or {
  implicit val booleanAndMonoid: MyMonoid[Boolean] =
    new MyMonoid[Boolean] {
      def combine(a: Boolean, b: Boolean) = a || b
      def empty = false
    }

  def test: Unit = {
    println(Laws.associativeLaw(true, true, false))
    println(Laws.identityLaw(true))
  }
}



object Go extends App {
  println("And")
  And.test
  println("")
  println("Or")
  Or.test
}