package checkout.v1.models

import checkout.v1.models.models.ItemCount
import org.scalatest.{Matchers, WordSpec}
import eu.timepit.refined.auto._
import eu.timepit.refined.numeric.Positive
import eu.timepit.refined.refineV

class DiscountTest extends WordSpec with  Matchers {
  val price = Price(5, 25)
  val count: ItemCount = 3

  "creating a discount" should {
    "not be possible if the discount price is greater than the items total" in {
      Discount(Item("test", price), count, Price.multiply(price, refineV[Positive](count + 1).toOption.get)) shouldBe None
    }

    "should be fine if the discount price is lesser than the items total" in {
      Discount(Item("test", price), count, Price(3)) shouldBe Option(new Discount(Item("test", price), count, Price(3)))
    }
  }
}
