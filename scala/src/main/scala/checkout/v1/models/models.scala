package checkout.v1.models

import eu.timepit.refined.api.Refined
import eu.timepit.refined.numeric.{Interval, Positive}
import eu.timepit.refined._

package object models {
  type Pounds = Int Refined ValidPound
  type Penny  = Int Refined ValidPenny
  type ItemCount = Int Refined Positive
  type PositiveInt = Int Refined Positive

  type ValidPound = Interval.Closed[W.`0`.T, W.`1000000`.T]
  type ValidPenny = Interval.Closed[W.`0`.T, W.`99`.T]
}
