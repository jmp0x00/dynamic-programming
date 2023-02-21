package knapsack

import zio.{System => _, _}
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
    if (capacity <= 0 || items.isEmpty) {
      0
    } else {
      val dp = Array.ofDim[Int](items.length, capacity + 1)

      for (i <- items.indices) {
        dp(i).update(0, 0)
      }

      for (c <- 0 to capacity) {
        if (items(0).weight <= c)
          dp(0).update(c, items(0).profit)
      }

      for (i <- 1 until items.length) {
        for (c <- 1 to capacity) {
          val profit1 = if (items(i).weight <= c) items(i).profit + dp(i - 1)(c - items(i).weight) else 0
          val profit2 = dp(i - 1)(c)
          dp(i).update(c, profit1 max profit2)
        }
      }

      printSelectedItems(dp, items, capacity)

      dp(items.length - 1)(capacity)
    }
  }

  private def printSelectedItems(dp: Array[Array[Int]], items: Array[Item], capacity: Int): Unit = {
    System.out.print("Selected weights:")
    var currentCapacity = capacity
    var totalProfit = dp(items.length - 1)(capacity)
    for (i <- (items.length - 1) until 0 by -1) {
      if (totalProfit != dp(i-1)(currentCapacity)) {
        System.out.print(" " + items(i).weight)
        currentCapacity -= items(i).weight
        totalProfit -= items(i).profit
      }
    }

    if (totalProfit != 0)
      System.out.print(" " + items(0).weight)
    System.out.println("")
  }
}
