package checkout.v1.models

import models._
import com.softwaremill.quicklens._
import eu.timepit.refined.auto._

import scala.annotation.tailrec

case class Item(name: String, price: Price)

case class Price(pounds: Pounds = 0, penny: Penny = 0)

object Price {
  import eu.timepit.refined._
  import eu.timepit.refined.auto._

  val PENNY_MAX_VALUE = 100

  def isGreater(p1: Price, p2: Price): Boolean = {
    val morePounds = p1.pounds.value > p2.pounds.value
    val samePoundsMorePenny = p1.pounds.value == p2.pounds.value && p1.penny.value > p2.penny.value
    morePounds || samePoundsMorePenny
  }

  def multiply(price: Price, times: PositiveInt): Price = {
    @tailrec def go(currentPrice: Price, times: Int): Price = {
      times match {
        case 1 => currentPrice
        case _ => go(add(currentPrice, price), times - 1)
      }
    }

    go(price, times.value)
  }

  def add(price: Price, other: Price): Price = {
    price
      .modify(_.pounds).using(_ => addPounds(price)(other))
      .modify(_.penny).using(addPenny(_)(other.penny))
  }

  private def addPounds: Price => Price => Pounds = {
    old: Price => (amount: Price) => {
      val overflow = computeOverflow(old.penny)(amount.penny)
      val poundsTotal = old.pounds.value + amount.pounds.value
      val total = poundsTotal + overflow
      refineV[ValidPound](total).fold(_ => 0, r => r)
    }
  }

  private def computeOverflow: Penny => Penny => Pounds = {
    from: Penny => to: Penny => from.value + to.value match {
      case overflow if overflow >= PENNY_MAX_VALUE => refineV[ValidPound](overflow / PENNY_MAX_VALUE).fold(_ => 0, r => r)
      case underflow                               => 0
    }
  }

  private def addPenny: Penny => Penny => Penny = {
    old: Penny => (amount: Penny) =>
      val notNormalized = old.value + amount.value
      val result = normalizePenny(notNormalized)
      result
  }

  private def normalizePenny: Int => Penny = old => refineV[ValidPenny](old % PENNY_MAX_VALUE).fold(_ => 0, r => r)
}
