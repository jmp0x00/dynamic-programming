package fibonacci

import zio._
import zio.Console._

object Fibonacci extends ZIOAppDefault {

  def run =
    for {
      _ <- print(5)
      _ <- print(6)
      _ <- print(7)
    } yield ()

  def print(n: Int): Task[Unit] =
    printLine(s"${n}th fibonacci is ---> ${calculate(n)}")

  def calculate(n: Int): Int =
    if (n < 2)
      n
    else
      calculate(n - 1) + calculate(n - 2)
}
