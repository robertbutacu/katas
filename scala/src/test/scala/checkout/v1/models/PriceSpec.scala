package checkout.v1.models

import org.scalatest.{Matchers, WordSpec}
import eu.timepit.refined.auto._

class PriceSpec extends WordSpec with Matchers {
  "addToPrice" should {
    "correctly add pounds to the current price" in {
      Price.add(Price(), Price(20)) shouldBe Price(20)
    }

    "correctly add pennies to the current price" in {
      Price.add(Price(), Price(20, 20)) shouldBe Price(20, 20)
    }
  }
}
