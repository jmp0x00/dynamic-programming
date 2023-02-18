package fibonacci

import org.scalacheck.Prop.forAll
import org.scalacheck._

object FibonacciSpec extends Properties("Fibonacci") {
  property("fib(0) = 0") = forAll(Gen.choose(0, 0)) { n =>
    Fibonacci.calculate(n) == 0
  }
  property("fib(1) = 1") = forAll(Gen.choose(1, 1)) { n =>
    Fibonacci.calculate(n) == 1
  }
  property("fib(n) = fib(n - 2) + fib(n - 1), n > 2") = forAll(Gen.choose(2, 10)) { n =>
    Fibonacci.calculate(n) == Fibonacci.calculate(n - 2) + Fibonacci.calculate(n - 1)
  }
}
