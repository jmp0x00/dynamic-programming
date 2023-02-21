import org.scalameter.api.{Bench, Gen}

object FibonacciBenchmark extends Bench.OfflineRegressionReport {
  private val numbers = Gen.range("number")(0, 1000000, 1000)

  performance of "Fibonacci" in {
    measure method "calculate" in {
      using(numbers) in {
        Fibonacci.calculate
      }
    }
  }
}
