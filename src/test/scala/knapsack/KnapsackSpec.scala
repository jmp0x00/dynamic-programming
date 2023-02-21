package knapsack

import knapsack.Knapsack.Item
import org.scalatest.matchers.must
import org.scalatest.wordspec.AnyWordSpec

class KnapsackSpec extends AnyWordSpec with must.Matchers {

  "getMaxProfit" should {
    "return maximum profit for capacity" in {
      val maxProfit = Knapsack.getMaxProfit(List(
        Item(name = "A", profit = 1, weight = 1),
        Item(name = "B", profit = 6, weight = 2),
        Item(name = "C", profit = 10, weight = 3),
        Item(name = "D", profit = 16, weight = 5),
      ), 7)
      maxProfit mustBe 22
    }
  }

}
