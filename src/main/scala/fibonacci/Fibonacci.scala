package fibonacci

import zio.Console._
import zio._

object Fibonacci extends ZIOAppDefault {

  def run: Task[Unit] =
    for {
      _ <- print(5)
      _ <- print(6)
      _ <- print(7)
    } yield ()

  def print(n: Int): Task[Unit] =
    printLine(s"${n}th fibonacci is ---> ${calculate(n)}")

  def calculate(n: Int): Int =
    calculateWithMemoization(n)

  def calculateRecursive(n: Int): Int =
    if (n < 2)
      n
    else
      calculate(n - 1) + calculate(n - 2)

  def calculateWithMemoization(n: Int): Int =
    calculateWithMemoizationInternal(Array.ofDim(n + 1), n)

  private def calculateWithMemoizationInternal(memoize: Array[Int], n: Int): Int =
      if (n < 2)
        n
      else if (memoize(n) != 0)
        memoize(n)
      else {
        memoize.update(
          n,
          calculateWithMemoizationInternal(memoize, n - 1) + calculateWithMemoizationInternal(memoize, n - 2)
        )
        memoize(n)
      }
}
