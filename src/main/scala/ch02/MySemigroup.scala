package ch02

trait MySemigroup[A] {
  def combine(x: A, y: A): A
}