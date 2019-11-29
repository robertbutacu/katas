package checkout.v1.models

import checkout.v1.models.models.{Penny, Pounds, ValidPenny, ValidPound}
import org.scalacheck.{Gen, Properties}
import org.scalacheck.Prop.forAll

class PriceProperties extends Properties("penny-properties") {
  private val PENNY_MAX = 99
  property("valid-pennies") = forAll (priceGenerator, priceGenerator) {
       (p1: Price, p2: Price) =>
         Price.add(p1, p2).penny.value < PENNY_MAX
    }
  import eu.timepit.refined._
  import eu.timepit.refined.auto._

  implicit val priceGenerator: Gen[Price] = for {
    price <- Gen.choose(0, Int.MaxValue).map(p => refineV[ValidPound](p).fold(_ => 0: Pounds, r => r: Pounds))
    penny <- Gen.choose(0, PENNY_MAX).map(p => refineV[ValidPenny](p).fold(_ => 0: Penny, r => r: Penny))
  } yield Price(price, penny)
}
