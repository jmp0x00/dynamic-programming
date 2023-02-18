package fibonacci

import org.scalameter.api._

object FibonacciBenchmark extends Bench.LocalTime {
  private val numbers = Gen.range("number")(0, 1000000, 10)

  performance of "Fibonacci" in {
    measure method "calculate" in {
      using(numbers) in {
        Fibonacci.calculate
      }
    }
  }
}
