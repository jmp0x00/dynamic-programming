package knapsack

import zio._
import zio.Console._

object Knapsack extends ZIOAppDefault {

  case class Item(name: String, weight: Int, profit: Int)

  override def run: Task[Unit] = {
    val items = List(
      Item(name = "A", profit = 1, weight = 1),
      Item(name = "B", profit = 6, weight = 2),
      Item(name = "C", profit = 10, weight = 3),
      Item(name = "D", profit = 16, weight = 5),
    )
    for {
      _ <- print(items, 7)
      _ <- print(items, 6)
    } yield ()
  }

  private def print(items: Iterable[Item], capacity: Int): Task[Unit] =
    for {
      maxProfit <- ZIO.attempt(getMaxProfit(items, capacity))
      _         <- printLine(s"Total knapsack profit for capacity $capacity ---> $maxProfit")
    } yield ()

  def getMaxProfit(items: Iterable[Item], capacity: Int): Int =
    getMaxProfitRecursive(items.toArray, capacity)

  def getMaxProfitRecursive(items: Array[Item], capacity: Int): Int = {
    val memoize = Array.fill[Option[Int]](items.length, capacity + 1)(None)

    def get(currentCapacity: Int, currentIndex: Int): Int =
      if (currentCapacity <= 0 || currentIndex >= items.length) {
        0
      } else if (memoize(currentIndex)(currentCapacity).nonEmpty) {
        memoize(currentIndex)(currentCapacity).get
      } else {
        val skip   = get(currentCapacity, currentIndex + 1)
        val select =
          if (items(currentIndex).weight <= currentCapacity)
            items(currentIndex).profit + get(currentCapacity - items(currentIndex).weight, currentIndex + 1)
          else
            0
        memoize(currentIndex).update(currentCapacity, Some(skip max select))
        memoize(currentIndex)(currentCapacity).get
      }

    get(capacity, 0)
  }
}
