package checkout.v1.services

import checkout.v1.models.{Discount, Item, Price}
import eu.timepit.refined.auto._
import org.scalatest.{Matchers, WordSpec}

class CheckoutServiceSpec extends WordSpec with Matchers {
  val item = Item("some-random-name", Price(20))
  val someRandomItemDiscount: Discount = Discount(item, 3, Price(5)).get

  val checkoutService: CheckoutServiceAlgebra = new CheckoutService
  "basketTotalPrice" should {
    "correctly price a list of items with 1 element" in {
      checkoutService.basketTotalPrice(List(item), Set.empty) shouldBe Price(20)
    }

    "correctly sum a list of same items" in {
      checkoutService.basketTotalPrice(List(item, item), Set.empty) shouldBe Price(40)
    }

    "correctly apply a discount for an item" in {
      checkoutService.basketTotalPrice(List(item, item, item), Set(someRandomItemDiscount)) shouldBe Price(55)
    }
  }
}
