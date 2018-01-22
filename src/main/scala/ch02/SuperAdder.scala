package ch02

import cats.instances.int._
import cats.instances.option._
import cats.Monoid
import cats.syntax.semigroup._

object SuperAdder {

  def add(items: List[Int]): Int = {
    items.foldLeft(0)(_ + _)
  }

  def add2(items: List[Int]): Int = {
    items.foldLeft(Monoid[Int].empty)(_ |+| _)
  }

  def add[A](items: List[A])(implicit m: Monoid[A]): A = {
    items.foldLeft(m.empty)(_ |+| _)
  }

  def add2[A: Monoid](items: List[A]): A = {
    items.foldLeft(Monoid[A].empty)(_ |+| _)
  }
}

object Go2 extends App {
  println(SuperAdder.add(List(1, 4, 5)))
  println(SuperAdder.add(List(10, 4, 5)))

  println(SuperAdder.add(List(Option(1), Option(4), Option(5))))
  println(SuperAdder.add2[Option[Int]](List(None, None, None)))

  implicit val orderAdder: Monoid[Order] =
    new Monoid[Order] {
      def combine(x: Order, y: Order): Order =
        Order(x.totalCost + y.totalCost, x.quantity + y.quantity)

      def empty: Order = Order(0, 0)
    }

  println(SuperAdder.add(List(Order(10, 2), Order(1, 100))))
}