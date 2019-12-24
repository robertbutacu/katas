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

  "multiplying Price with 1" should {
    "return the price back" in {
      Price.multiply(Price(10, 15), 1) shouldBe Price(10, 15)
    }
  }

  "multiplying Price with a random int" should {
    "give back the Price times that int" in {
      Price.multiply(Price(20, 20), 5) shouldBe Price(101)
    }
  }

  "isGreater" should {
    "return true if p1 is bigger than p2" in {
      Price.isGreater(Price(10, 10), Price(9)) shouldBe true
    }
    "return false if p1 is smaller than p2" in {
      Price.isGreater(Price(), Price(1)) shouldBe false
    }
    "return false if p1 is equal to p2" in {
      Price.isGreater(Price(10, 10), Price(10, 10)) shouldBe false
    }
  }
}
