package checkout.v1.services

import checkout.v1.models.{Discount, Item, Price}

trait CheckoutServiceAlgebra {
  def basketTotalPrice(items: List[Item], availableDiscounts: Set[Discount]): Price
}

class CheckoutService extends CheckoutServiceAlgebra {
  override def basketTotalPrice(item: List[Item], availableDiscounts: Set[Discount]): Price = {
    val groupedItems: Map[String, List[Item]] = item.groupBy(_.name)

    val discountsForBasket = availableDiscounts.filter {
      d =>
        groupedItems.exists {
          case (itemName, items) => itemName == d.item.name && items.size >= d.count.value
        }
    }

    val discountAmount = discountsForBasket.foldLeft(Price())((total, current) => Price.add(total, current.discountAmount))

    val basketTotal = item.foldLeft(Price())((total, current) => Price.add(total, current.price))

    //Price.minus(basketTotal, discountAmount)
    ???
  }
}
