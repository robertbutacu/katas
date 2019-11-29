package checkout.v1.services

import checkout.v1.models.{Item, Price}
import eu.timepit.refined.auto._
import org.scalatest.{Matchers, WordSpec}

class CheckoutServiceSpec extends WordSpec with Matchers {
  val checkoutService: CheckoutServiceAlgebra = new CheckoutService

  "basketTotalPrice" should {
    "correctly price a list of items with 1 element" in {
      val item = Item("some-random-name", Price(20, 0))
      checkoutService.basketTotalPrice(List(item)) shouldBe Price(20, 0)
    }

    "correctly sum a list of same items" in {
      val item = Item("some-random-name", Price(20, 0))
      checkoutService.basketTotalPrice(List(item, item)) shouldBe Price(40, 0)
    }
  }
}
