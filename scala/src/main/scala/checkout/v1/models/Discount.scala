package checkout.v1.models

import checkout.v1.models.models.ItemCount

case class Discount(item: Item, count: ItemCount, discountAmount: Price)

object Discount {
  def apply(item: Item, count: ItemCount, discountAmount: Price): Option[Discount] = {
    if(Price.isGreater(discountAmount, Price.multiply(item.price, count))) None
    else Option(new Discount(item, count, discountAmount))
  }
}
