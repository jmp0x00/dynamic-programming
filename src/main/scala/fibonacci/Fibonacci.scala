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
    calculateWithTabulation(n)

  def calculateRecursive(n: Int): Int =
    if (n < 2)
      n
    else
      calculateRecursive(n - 1) + calculateRecursive(n - 2)

  def calculateWithMemoization(n: Int): Int = {
    def get(memoize: Array[Int], n: Int): Int =
      if (n < 2)
        n
      else if (memoize(n) != 0)
        memoize(n)
      else {
        memoize.update(
          n,
          get(memoize, n - 1) + get(memoize, n - 2)
        )
        memoize(n)
      }
    get(Array.ofDim(n + 1), n)
  }

  def calculateWithTabulation(n: Int): Int =
    if (n < 2)
      n
    else {
      val table: Array[Int] = Array.ofDim(n + 1)

      // base cases
      table.update(0, 0)
      table.update(1, 1)

      for (i <- 2 to n)
        table.update(i, table(i - 2) + table(i - 1))

      table(n)
    }
}
