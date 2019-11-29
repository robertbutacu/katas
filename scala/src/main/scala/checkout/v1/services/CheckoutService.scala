package checkout.v1.services

import checkout.v1.models.{Item, Price}

trait CheckoutServiceAlgebra {
  def basketTotalPrice(items: List[Item]): Price
}

class CheckoutService extends CheckoutServiceAlgebra {
  override def basketTotalPrice(item: List[Item]): Price = {
    import eu.timepit.refined.auto._

    item.foldLeft(Price(0, 0))((total, current) => Price.add(total, current.price))
  }
}
